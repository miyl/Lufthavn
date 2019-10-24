package dk.kea.departments;

import dk.kea.client.ServerHandler;
import dk.kea.models.Flight;
import dk.kea.shared.Time;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;

public class FuelHandler extends ServerHandler {

    Date newDate = new Date();

    public FuelHandler(Socket socket, ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream){
        super( socket, objectInputStream, objectOutputStream);
    }

    public void start(){

        while (isConnected()) {

            if (getFlightList().size()>0)
            {
                for (Flight flight : flightList)
                {
                    if (flight.getFlightSize().equalsIgnoreCase("lille"));
                    {
                        newDate.setTime(flight.getExpectedDeparture().getTime() / Time.seconds + Time.fuelLille);
                        flight.setExpectedDeparture(newDate);
                    }

                    if (flight.getFlightSize().equalsIgnoreCase("mellem"));
                    {
                        newDate.setTime(flight.getExpectedDeparture().getTime() / Time.seconds + Time.fuelMellem);
                        flight.setExpectedDeparture(newDate);
                    }

                    if (flight.getFlightSize().equalsIgnoreCase("stor"));
                    {
                        newDate.setTime(flight.getExpectedDeparture().getTime() / Time.seconds + Time.fuelStor);
                        flight.setExpectedDeparture(newDate);
                    }

                }



                sender.sendPlanes(getFlightList());
            }
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
