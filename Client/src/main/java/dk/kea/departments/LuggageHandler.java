package dk.kea.departments;

import dk.kea.client.ServerHandler;
import dk.kea.models.Flight;
import dk.kea.shared.Time;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LuggageHandler extends ServerHandler {

    Date newDate = new Date();

    public LuggageHandler(Socket socket, ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream){
        super(socket, objectInputStream, objectOutputStream);
    }

    public void start(){



        while (isConnected()) {

            List<Flight> flights = getFlightList();

            if(flights.size()>0){
                for(Flight flight : flights){
                    if(flight.getGate().getGateSize().equals("lille")){
                        newDate.setTime(flight.getExpectedDeparture().getTime() / Time.seconds + Time.bagageUdLille);
                        flight.setExpectedDeparture(newDate);
                    }
                    if(flight.getGate().getGateSize().equals("mellem")){
                        newDate.setTime(flight.getExpectedDeparture().getTime() / Time.seconds + Time.bagageUdMellem);
                        flight.setExpectedDeparture(newDate);
                    }
                    if(flight.getGate().getGateSize().equals("stor")){
                        newDate.setTime(flight.getExpectedDeparture().getTime() / Time.seconds + Time.bagageUdStor);
                        flight.setExpectedDeparture(newDate);
                    }
                }
            }

            sender.sendPlanes(flights);

            //Får information fra keyboardet - hvis der er noget.
//            System.out.printf("> ");
//            if (keyboard.getReader().hasNextLine()) {
//                String[] tokens = keyboard.getReader().nextLine().toUpperCase().split(" ");
//
//                switch(tokens[0])
//                {
//                    case "EXIT":
//                        close();
//                        break;
//                    case "LIST":
//                        if(getFlightList().size() > 0)
//                        {
//                            System.out.print("[INFO]: Active planes in this department:\n\n");
//
//                            getFlightList().forEach(plane -> System.out.print("      [" + plane.getName() + "]\n"));
//
//                            System.out.println();
//                        } else {
//                            System.out.print("[INFO]: No active planes in this department.\n");
//                        }
//                        break;
//                    case "SEND":
//                        if(getFlightList().size() > 0){
//                            sender.sendPlanes(getFlightList());
//                            System.out.println("[INFO]: Flights is send to server");
//                        } else {
//                            System.out.print("[ERROR]: No active planes in this apartment.\n");
//                        }
//                        break;
//                    case "REMOVE":
//                        if(getFlightList().size() > 0)
//                        {
//                            getFlightList().forEach(plane -> System.out.print("      [" + plane.getName() + "]\n"));
//                            System.out.println("      removed from local list.");
//                            removeFlightList();
//                        } else {
//                            System.out.print("[INFO]: No active planes in this department.\n");
//                        }
//
//                        break;
//                    default:
//                        sender.send(String.join(" ", tokens), false);
//                        break;
//                }
//            }
        }
    }
}
