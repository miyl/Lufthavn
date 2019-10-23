package dk.kea.handlers;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import dk.kea.shared.Flights;

public class DepartmentHandler implements Runnable {

    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private boolean isRunning = true;

    public String name;

    public DepartmentHandler(Socket socket, ObjectInputStream input, ObjectOutputStream output, String name) {
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
                output.flush();
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

    public boolean sendPlane(Flights airplane)
    {
        try {
            output.writeObject(airplane);
            output.flush();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}



