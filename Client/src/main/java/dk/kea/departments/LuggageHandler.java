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
import java.util.Date;
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

                           getFlightList().forEach(plane -> System.out.print("      [" + plane.getId() + ", " + plane.getExpectedDeparture().getTime() + "]\n"));

                           System.out.println();
                       } else {
                           System.out.print("[INFO]: No active planes in this department.\n");
                       }
                       break;
                   case "SEND":
                       if(getFlightList().size() > 0){
                           getFlightList().forEach(plane -> System.out.print("      [" + plane.getId() + ", " + plane.getExpectedDeparture().getTime() + "]\n"));
                           sender.sendPlanes(getFlightList());
                           System.out.println("[INFO]: Flights is send to server");
                       } else {
                           System.out.print("[ERROR]: No active planes in this apartment.\n");
                       }
                       break;
                   case "REMOVE":
                       if(getFlightList().size() > 0)
                       {
                           getFlightList().forEach(plane -> System.out.print("      [" + plane.getId() + "]\n"));
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
                       System.out.print("[INFO]: Not a command.\n");
                       break;
               }
           }
        }
    }


    public void manipulate()
    {
        if(getFlightList().size() > 0){ 
            getFlightList().forEach(flight -> {
                if(flight.getFlightSize().equalsIgnoreCase("LILLE"))
                {
                    System.out.println("Updating: "+ flight.getId());
                    Date newDate = new Date();
                    newDate.setTime(flight.getExpectedDeparture().getTime() + 86400000);
                    flight.setExpectedDeparture(newDate);
                }
            }
            );
            System.out.println("[INFO] Flight manipulated.");
        }
    }
}
