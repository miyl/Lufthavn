package dk.kea.departments;

import dk.kea.client.ServerHandler;
import dk.kea.shared.Time;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Timestamp;

public class FuelHandler extends ServerHandler {


    public FuelHandler(Socket socket, ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream){
        super( socket, objectInputStream, objectOutputStream);
    }

    public void start() {

        while (isConnected()) {

            // Får information fra keyboardet - hvis der er noget.
            System.out.printf("> ");
            if (keyboard.getReader().hasNextLine()) {
                String[] tokens = keyboard.getReader().nextLine().toUpperCase().split(" ");

                switch (tokens[0]) {
                case "EXIT":
                    close();
                    break;
                case "LIST":
                    if (getFlightList().size() > 0) {
                        System.out.print("[INFO]: Active planes in this department:\n\n");

                        getFlightList().forEach(plane -> System.out.print(
                                "      [" + plane.getId() + ", " + plane.getExpectedDeparture() + "]\n"));

                        System.out.println();
                    } else {
                        System.out.print("[INFO]: No active planes in this department.\n");
                    }
                    break;
                case "SEND":
                    if (getFlightList().size() > 0) {
                        manipulate();
                        getFlightList().forEach(plane -> System.out.print(
                                "      [" + plane.getId() + ", " + plane.getExpectedDeparture() + "]\n"));
                        sender.sendPlanes(getFlightList());
                        System.out.println("[INFO]: Flights is send to server");
                    } else {
                        System.out.print("[ERROR]: No active planes in this apartment.\n");
                    }
                    break;
                case "REMOVE":
                    if (getFlightList().size() > 0) {
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

    public void manipulate() {
        if (getFlightList().size() > 0) {
            getFlightList().forEach(flight -> {
                long currentTime = flight.getExpectedDeparture().getTime();
                if (flight.getFlightSize().equalsIgnoreCase("LILLE")) {
                    System.out.println("Updating: " + flight.getId());
                    flight.setExpectedDeparture(new Timestamp(currentTime + Time.milliseconds * Time.bagageIndLille));
                } else if (flight.getFlightSize().equalsIgnoreCase("MELLEM")) {
                    System.out.println("Updating: " + flight.getId());
                    flight.setExpectedDeparture(new Timestamp(currentTime + Time.milliseconds * Time.bagageIndMellem));
                } else if (flight.getFlightSize().equalsIgnoreCase("STOR")) {
                    System.out.println("Updating: " + flight.getId());
                    flight.setExpectedDeparture(new Timestamp(currentTime + Time.milliseconds * Time.bagageIndStor));
                }
            });
            System.out.println("[INFO] Flight manipulated.");
        }
    }
}
