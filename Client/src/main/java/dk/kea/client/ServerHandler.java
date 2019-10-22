package dk.kea.client;

import java.io.IOException;
import java.net.Socket;

import dk.kea.App;

public class ServerHandler {

    private Socket socket = null;
    public Sender sender;
    public Reader reader;
    public String name;

    public Boolean connected = false;

    public ServerHandler(String name) {
        this.name = name;

        try {
            socket = new Socket(App.address, App.port);

            sender = new Sender(this, socket);
            reader = new Reader(this, socket);

            Thread readerthread = new Thread(reader);
            readerthread.start();

            if (sender.hasStream() && reader.hasStream()) {
                this.connected = true;
                sender.send(name);
            }

        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    public void close(){
        connected = false;
        try {
            socket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public boolean getConnected()
    {
        return connected;
    }
}
