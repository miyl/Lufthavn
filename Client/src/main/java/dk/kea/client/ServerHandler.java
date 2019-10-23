package dk.kea.client;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;

import dk.kea.App;
import dk.kea.shared.Keyboard;

public class ServerHandler {

    private Socket socket = null;
    public Sender sender;
    public Reader reader;
    public Keyboard keyboard;

    private Boolean connected = false;

    public ServerHandler() {
        String username = "";
        String password = "";
        String credentials = "";
        keyboard = new Keyboard(this);

        System.out.println("Enter username:");
        if(keyboard.getReader().hasNextLine()){
            username = keyboard.getReader().nextLine();
        }
        System.out.println("Enter password:");
        if(keyboard.getReader().hasNextLine()){
            password = keyboard.getReader().nextLine();
        }
        credentials = username + ";" + password;

        try {
            socket = new Socket(App.address, App.port);

            if (socket.isConnected()) {
                sender = new Sender(this, socket);
                reader = new Reader(this, socket);

                Thread readerthread = new Thread(reader);
                readerthread.start();

                if (sender.hasStream() && reader.hasStream()) {
                    this.connected = true;
                    sender.send(credentials);
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
}
