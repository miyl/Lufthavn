package dk.kea.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import dk.kea.App;

public class ServerHandler {

    private Socket socket = null;
    private Sender sender;
    private Reader reader;

    Boolean connected = false;

    public ServerHandler() {
        try {
            socket = new Socket(App.address, App.port);

            sender = new Sender(this, socket);
            reader = new Reader(this, socket);

            Thread readerthread = new Thread(reader);
            readerthread.start();

            if (sender.hasStream() && reader.hasStream()) {
                this.connected = true;
            }

            loop();

        } catch (IOException e) {
            System.out.println(e);
        }

        
    }

    public void loop(){
        while (connected) {
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            String line;
            try {
                line = keyboard.readLine();
                sender.send(line);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        close();
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
