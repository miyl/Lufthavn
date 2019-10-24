package dk.kea.departments;

import dk.kea.client.ServerHandler;
import dk.kea.models.Flight;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class CleanHandler extends ServerHandler {
    public CleanHandler(Socket socket, ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream){
        super(socket, objectInputStream, objectOutputStream);
    }
    public void start(){

        while (isConnected()) {

            //Får information fra keyboardet - hvis der er noget.
            System.out.printf("> ");
            if (keyboard.getReader().hasNextLine()) {
                String[] tokens = keyboard.getReader().nextLine().split(" ");

                if(tokens[0].equalsIgnoreCase("EXIT"))
                {
                    close();
                    break;                    
                } else if (tokens[0].equalsIgnoreCase("FLIGHT")) {
                    sender.sendPlane(new Flight(tokens[1]));
                } else if(tokens[0].equalsIgnoreCase("LIST")) {

                    if(getFlightList().size() > 0)
                    {
                        System.out.print("[INFO]: Active planes in this department:\n\n");
                        getFlightList().forEach(value -> value.forEach(plane -> System.out.print(plane.getName())));
                        System.out.println();
                    } else {
                        System.out.print("[INFO]: No active planes in this department.\n");
                    }
                } else {
                    //Sender information til serveren
                    sender.send(String.join(" ", tokens), false);
                }
                
            }
        }
    }
}
