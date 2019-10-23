package dk.kea.handlers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class DepartmentHandler implements Runnable {

    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;
    private boolean isRunning = true;

    public String name;

    public DepartmentHandler(Socket socket, DataInputStream input, DataOutputStream output, String name) {
        this.socket = socket;
        this.input = input;
        this.output = output;
        this.name = name;


        System.out.println(name + " department thread startet.");

    }

    @Override
    public void run() {
        try {
            // Dette er bare en test
            while (isRunning) {
                String message = input.readUTF();
                output.writeUTF(message);
                System.out.printf(name + " thread: %s \n", message );

            }

            input.close();
            output.close();
            socket.close();

        } catch (IOException e) {
            System.out.println("** " + name + " thread exited  **");

            isRunning = false;
        }
    }
}

