package dk.kea.handlers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

public class LuggageDepartmentHandler implements Runnable {

    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;
    private boolean isRunning = true;

    public LuggageDepartmentHandler(Socket socket, DataInputStream dataInputStream, DataOutputStream dataOutputStream) {
        this.socket = socket;
        this.input = dataInputStream;
        this.output = dataOutputStream;


        System.out.println("Luggage department thread startet.");

    }

    @Override
    public void run() {
        try {
            // Dette er bare en test
            while (isRunning) {

                String message = input.readUTF();
                output.writeUTF(message);
                System.out.printf("Luggage thread: %s \n", message );

            }

            input.close();
            output.close();
            socket.close();

        } catch (IOException e) {
            System.out.println("** Luggage thread exited  **");

            isRunning = false;
        }
    }
}
