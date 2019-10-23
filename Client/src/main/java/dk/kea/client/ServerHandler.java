package dk.kea.client;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.util.ArrayList;

import dk.kea.App;
import dk.kea.shared.Flights;
import dk.kea.shared.Keyboard;

public class ServerHandler {

    private Socket socket = null;
    public String name;
    public Sender sender;
    public Reader reader;
    public Keyboard keyboard;
    public ArrayList<Flights> flightList = new ArrayList<>();

    private Boolean connected = false;

    public ServerHandler(Socket socket) {

        keyboard = new Keyboard(this);

        try {
            this.socket = socket;

            if (this.socket.isConnected()) {
                System.out.println("Is connected");
                sender = new Sender(this, socket);
                reader = new Reader(this, socket);

                Thread readerthread = new Thread(reader);
                readerthread.start();


                if (sender.hasStream() && reader.hasStream()) {
                    this.connected = true;
                }

                Thread keyboardThread = new Thread(keyboard);
                keyboardThread.start();

                System.out.println("[SUCCESS]: Connected to server!");
                System.out.println("[INFO]: back to MENU with 'exit' command");

            }
        } catch (ConnectException e) {
            System.out.println("[ERROR] Connection failed..");
        } catch (IOException e){
            System.out.println(e);
        }
    }
    
    public void close(){
        System.out.println("Closing connection..");
        connected = false;
        sender.close();
        reader.close();
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isConnected()
    {
        return connected;
    }

    public ArrayList<Flights> getFlightList()
    {
        return flightList;
    }

    public void addFlightToList(Flights airplane)
    {
        flightList.add(airplane);
    }
}
