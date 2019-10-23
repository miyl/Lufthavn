package dk.kea.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import dk.kea.shared.Flights;

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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Flights read() {
        try {
            return (Flights) input.readObject();
        } catch (ClassNotFoundException | IOException e) {
            return null;
        }
        
    }

    public boolean hasStream()
    {
        return null != input;
    }

    public void readPlanes()
    {
        Flights airplane = read();
        client.addFlightToList(airplane);

    }
    
}