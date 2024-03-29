package dk.kea.handlers;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.io.Serializable;

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

            }

            input.close();
            output.close();
            socket.close();

        } catch (IOException e) {
            System.out.println("** " + name + " thread exited  **");

            isRunning = false;
        }
    }

    public void sendSingle(Serializable serialized) {
        try {
            output.writeObject(serialized);
            output.flush();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void sendList(List<? extends Serializable> serializedList) {
        try {
            output.writeObject(serializedList);
            output.flush();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void sendNumber(String number) {
        try {
            output.writeObject((String) number);
            output.flush();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public List<Flight> readList() {

        try {
            return (List<Flight>) input.readObject();
        } catch (ClassNotFoundException | IOException e) {
            return null;
        }

    }

    public Flight readSingle() {

        try {
            return (Flight) input.readObject();
        } catch (ClassNotFoundException | IOException e) {
            return null;
        }
        
    }
    
}



