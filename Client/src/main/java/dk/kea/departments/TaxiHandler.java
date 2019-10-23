package dk.kea.departments;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import dk.kea.App;
import dk.kea.client.ServerHandler;
import dk.kea.shared.Flight;
import java.net.Socket;

public class TaxiHandler extends ServerHandler
{

    public TaxiHandler(Socket socket, ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream){
        super( socket, objectInputStream, objectOutputStream);
    }

    public void start(){

        while (isConnected()) {

            //Får information fra keyboardet - hvis der er noget.
            System.out.printf("> ");
            if (keyboard.getReader().hasNextLine()) {
                String[] tokens = keyboard.getReader().nextLine().toUpperCase().split(" ");

                switch(tokens[0])
                {
                    case "EXIT":
                        close();
                        break;
                    case "LIST":
                        if(getFlightList().size() > 0)
                        {
                            System.out.print("[INFO]: Active planes in this department:\n\n");

                            getFlightList().forEach(value -> System.out.print(
                                "        [" + value.getId() + ", " + value.getName() + "]\n"
                                ));

                            System.out.println();
                        } else {
                            System.out.print("[INFO]: No active planes in this department.\n");
                        }
                        break;
                    case "ADD":
                        if(tokens.length == 2){
                            sender.sendPlane(new Flight(tokens[1]));
                            System.out.println("[INFO]: Flight [" + tokens[1] + "] added to queue.");
                        } else {
                            System.out.print("[ERROR]: Use syntax.\n");
                            System.out.print("         ADD <name>\n");
                        }                        
                        break;
                    case "REMOVE":
                        if(getFlightList().size() > 0)
                        {
                            System.out.println("[INFO]: Flight [" + getFlightList().get(0).getName() + "] removed from queue.");
                            removeFlightToList();
                        } else {
                            System.out.print("[INFO]: No active planes in this department.\n");
                        }

                        break;
                    default:
                        sender.send(String.join(" ", tokens), false);
                        break;
                }                
            }
        }
    }
}
