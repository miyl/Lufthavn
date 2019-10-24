package dk.kea.handlers;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import dk.kea.models.Flight;

public class DepartmentHandler implements Runnable {

    public Socket socket;
    public ObjectInputStream input;
    public ObjectOutputStream output;
    public boolean isRunning = true;

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
                List<Flight> airPlaneList = read();
                airPlaneList.forEach(value -> System.out.print(
                    "        [" + value.getId() + ", " + value.getName() + "]\n"
                    ));
            }

            input.close();
            output.close();
            socket.close();

        } catch (IOException e) {
            System.out.println("** " + name + " thread exited  **");

            isRunning = false;
        }
    }

    public void send(List<Flight> airplane) {
        try {
            output.writeObject(airplane);
            output.flush();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public List<Flight> read() {
        try {
            return (List<Flight>) input.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    
}



