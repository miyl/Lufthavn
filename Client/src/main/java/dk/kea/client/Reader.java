package dk.kea.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;

import dk.kea.models.Flight;

public class Reader implements Runnable {
    private ServerHandler client;
    private ObjectInputStream input = null;

    public Reader(ServerHandler client, Socket socket, ObjectInputStream objectInputStream) throws IOException {
        this.client = client;
        this.input = objectInputStream;
        ;
    }

    @Override
    public void run() {
        while (client.isConnected()) {
            readPlanes();
        }
        close();
    }

    public void close() {
        try {
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean hasStream() {
        return null != input;
    }

    public void readPlanes() {
        List<Flight> airplaneList;
        try {
            airplaneList = (List<Flight>) input.readObject();
            client.updateFlightList(airplaneList);
            System.out.print("...\n[INFO] A list of planes is recieved\n> ");
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }      
    }
}
