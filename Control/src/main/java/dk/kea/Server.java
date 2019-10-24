package dk.kea;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import dk.kea.handlers.CleaningDepartmentHandler;
import dk.kea.handlers.FuelDepartmentHandler;
import dk.kea.handlers.LuggageDepartmentHandler;
import dk.kea.handlers.TaxiDepartmentHandler;
import dk.kea.management.Users;

public class Server implements Runnable {

    //initialize socket and input stream
    private static Socket socket = null;
    private static ServerSocket server = null;

    private TaxiDepartmentHandler taxi = null;
    private CleaningDepartmentHandler clean = null;
    private FuelDepartmentHandler fuel = null;
    private LuggageDepartmentHandler luggage = null;

    // constructor with port
    public Server(int port) {

        // starts server and waits for a connection
        try {
            server = new ServerSocket(5000);
            System.out.println("** Server started  **");

        } catch (IOException i) {
            System.out.println(i);
        }
    }


    @Override
    public void run() {
        try {
        while (true) {

            System.out.println("** Waiting for clients ... **");
            socket = server.accept();
            System.out.println("** Someone connected ... **");


            ObjectOutputStream output  = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());


            Users userManage = new Users();

            String message = input.readUTF();
            String[] splitMessage = message.split(";");
            if(userManage.chkCredentials(splitMessage[0], splitMessage[1])){
                String afdeling =userManage.getAfdeling(splitMessage[0]);
                output.writeUTF("Ok" + ";" + afdeling);
                output.flush();

                switch (afdeling) {
                    case "Taxi":
                        System.out.printf("** Client accepted %s ** \n", splitMessage[0]);
                        taxi = new TaxiDepartmentHandler(socket, input, output);
                        Thread taxiThread = new Thread(taxi);
                        taxiThread.start();
                        break;

                    case "Rengøring":
                        System.out.printf("** Client accepted %s ** \n", splitMessage[0]);
                        clean = new CleaningDepartmentHandler(socket, input, output);
                        Thread cleanThread = new Thread(clean);
                        cleanThread.start();
                        break;

                    case "Brændstof":
                        System.out.printf("** Client accepted %s ** \n", splitMessage[0]);
                        fuel = new FuelDepartmentHandler(socket, input, output);
                        Thread fuelThread = new Thread(fuel);
                        fuelThread.start();
                        break;

                    case "Baggage":
                        System.out.printf("** Client accepted %s ** \n", splitMessage[0]);
                        luggage = new LuggageDepartmentHandler(socket, input, output);
                        Thread luggageThread = new Thread(luggage);
                        luggageThread.start();
                        break;

                    default:
                        System.out.println("Wrong input, closing connection");
                        socket.close();
                        input.close();
                        output.close();
                        break;
                }

            }
            else {
                System.out.println("** Someone was rejected **");
                output.writeUTF("notOk");
                output.flush();
                socket.close();
                input.close();
                output.close();
            }


        }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TaxiDepartmentHandler getTaxi() {
        return taxi;
    }

    public CleaningDepartmentHandler getClean() {
        return clean;
    }

    public FuelDepartmentHandler getFuel() {
        return fuel;
    }

    public LuggageDepartmentHandler getLuggage() {
        return luggage;
    }

    public boolean isAllConnected() {
        return getTaxi() != null;
    }
}
