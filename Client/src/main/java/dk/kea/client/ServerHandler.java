package dk.kea.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.ArrayList;

import dk.kea.App;
import dk.kea.models.Flight;
import dk.kea.shared.Keyboard;
import static dk.kea.statics.StaticLib.*;

public class ServerHandler {

    public Socket socket = null;
    public String name;
    public Sender sender;
    public Reader reader;
    public Keyboard keyboard;
    public ArrayList<Flight> flightList = new ArrayList<>();

    private Boolean connected = false;

    public ServerHandler(Socket socket, ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream) {

        keyboard = new Keyboard(this);

        try {
            this.socket = socket;

            if (this.socket.isConnected()) {
                clearScreen();
                sender = new Sender(this, socket, objectOutputStream);
                reader = new Reader(this, socket, objectInputStream);

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
        App.running = false;
    }

    public boolean isConnected()
    {
        return connected;
    }

    public ArrayList<Flight> getFlightList()
    {
        return flightList;
    }

    public void addFlightToList(Flight airplane)
    {
        flightList.add(airplane);
    }

    public void removeFlightToList()
    {
        flightList.remove(0);
    }
}
