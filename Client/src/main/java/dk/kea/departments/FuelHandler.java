package dk.kea.departments;

import dk.kea.client.ServerHandler;
import dk.kea.models.Flight;
import dk.kea.shared.Time;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Timestamp;

public class FuelHandler extends ServerHandler {


    public FuelHandler(Socket socket, ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream){
        super( socket, objectInputStream, objectOutputStream);
    }

    public void start(){

        while (isConnected()) {

            if (getFlightList().size()>0)
            {
                for (Flight flight : getFlightList())
                {
                    long currentTime = flight.getExpectedDeparture().getTime();

                    if (flight.getFlightSize().equalsIgnoreCase("lille"));
                    {
                        flight.setExpectedDeparture(new Timestamp(currentTime / Time.seconds + Time.fuelLille));
                    }

                    if (flight.getFlightSize().equalsIgnoreCase("mellem"));
                    {
                        flight.setExpectedDeparture(new Timestamp(currentTime / Time.seconds + Time.fuelMellem));
                    }

                    if (flight.getFlightSize().equalsIgnoreCase("stor"));
                    {
                        flight.setExpectedDeparture(new Timestamp(currentTime / Time.seconds + Time.fuelStor));
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
