package dk.kea.departments;

import dk.kea.client.ServerHandler;
import dk.kea.models.Flight;
import dk.kea.shared.Time;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Timestamp;
import java.util.List;

public class LuggageHandler extends ServerHandler {

    public LuggageHandler(Socket socket, ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream){
        super(socket, objectInputStream, objectOutputStream);
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

                           getFlightList().forEach(plane -> System.out.print("      [" + plane.getName() + ", " + plane.getExpectedDeparture() + "]\n"));

                           System.out.println();
                       } else {
                           System.out.print("[INFO]: No active planes in this department.\n");
                       }
                       break;
                   case "SEND":
                       if(getFlightList().size() > 0){
                           sender.sendPlanes(getFlightList());
                           System.out.println("[INFO]: Flights is send to server");
                       } else {
                           System.out.print("[ERROR]: No active planes in this apartment.\n");
                       }
                       break;
                   case "REMOVE":
                       if(getFlightList().size() > 0)
                       {
                           getFlightList().forEach(plane -> System.out.print("      [" + plane.getName() + "]\n"));
                           System.out.println("      removed from local list.");
                           removeFlightList();
                       } else {
                           System.out.print("[INFO]: No active planes in this department.\n");
                       }

                       break;
                   case "MANI":
                       manipulate();
                       break;
                   default:
                       sender.send(String.join(" ", tokens), false);
                       break;
               }
           }
        }
    }


    public void manipulate()
    {
        if(getFlightList().size() > 0){ 
            for(Flight flight : getFlightList()){
                if(flight.getGate().getGateSize().equals("lille")){
                    flight.setExpectedDeparture(new Timestamp(flight.getExpectedDeparture().getTime() + 86400000) );
                }
                else if(flight.getGate().getGateSize().equals("mellem")){
                    //flight.setExpectedDeparture(flight.getExpectedDeparture().getTime());
                }
                else {
                    //flight.setExpectedDeparture(flight.getExpectedDeparture().getTime());
                }
            }
        }
        System.out.println("[INFO] Flight manipulated.");
    }
}
