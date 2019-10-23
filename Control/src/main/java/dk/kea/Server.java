package dk.kea;

import dk.kea.handlers.CleaningDepartmentHandler;
import dk.kea.handlers.FuelDepartmentHandler;
import dk.kea.handlers.LuggageDepartmentHandler;
import dk.kea.handlers.TaxiDepartmentHandler;
import dk.kea.management.Users;

import java.net.*;
import java.io.*;

public class Server implements Runnable  {

    //initialize socket and input stream
    private static Socket socket = null;
    private static ServerSocket server = null;

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


            DataInputStream input  = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());

            //"brugernavn;kode
            String message = input.readUTF();
            String[] splitMessage = message.split(";");

            Users userManage = new Users();
            if(userManage.chkCredentials(splitMessage[0], splitMessage[1])){
                System.out.println(userManage.getAfdeling(splitMessage[0]));
                switch (userManage.getAfdeling(splitMessage[0])) {
                    case "taxi":
                        System.out.println("i taxa nu");
                        System.out.printf("** Client connected %s ** \n", message);
                        TaxiDepartmentHandler taxi = new TaxiDepartmentHandler(socket, input, output);
                        Thread taxiThread = new Thread(taxi);
                        taxiThread.start();
                        break;

                    case "clean":
                        System.out.printf("** Client connected %s ** \n", message);
                        CleaningDepartmentHandler clean = new CleaningDepartmentHandler(socket, input, output);
                        Thread cleanThread = new Thread(clean);
                        cleanThread.start();
                        break;

                    case "fuel":
                        System.out.printf("** Client connected %s ** \n", message);
                        FuelDepartmentHandler fuel = new FuelDepartmentHandler(socket, input, output);
                        Thread fuelThread = new Thread(fuel);
                        fuelThread.start();
                        break;

                    case "luggage":
                        System.out.printf("** Client connected %s ** \n", message);
                        LuggageDepartmentHandler luggage = new LuggageDepartmentHandler(socket, input, output);
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



        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}