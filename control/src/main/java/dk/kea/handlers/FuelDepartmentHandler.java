package dk.kea.handlers;

import java.io.*;
import java.net.Socket;

public class FuelDepartmentHandler implements Runnable {

    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;
    private boolean isRunning = true;

    public FuelDepartmentHandler(Socket socket, DataInputStream dataInputStream, DataOutputStream dataOutputStream) {
        this.socket = socket;
        this.input = dataInputStream;
        this.output = dataOutputStream;


        System.out.println("Fuel department thread startet.");

    }

    @Override
    public void run() {
        try {
            // Dette er bare en test
            while (isRunning) {

                String message = input.readUTF();
                output.writeUTF(message);
                System.out.printf("Fuel thread: %s \n", message );

            }

            input.close();
            output.close();
            socket.close();

        } catch (IOException e) {
            System.out.println("** Fuel thread exited  **");

            isRunning = false;
        }
    }
}
