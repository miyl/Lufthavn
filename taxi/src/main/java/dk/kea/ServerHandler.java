package dk.kea;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerHandler {

    private Socket socket = null;
    private DataInputStream inputStream = null;
    private DataOutputStream outputStream = null;
    private Sender sender;
    private Reader reader;

    Boolean connected = false;

    public ServerHandler(String address, String port) throws IOException {
        try {
            int intPort = Integer.parseInt(port);

            socket = new Socket(address, intPort);

            sender = new Sender(this, socket);
            reader = new Reader(this, socket);

            Thread readerthread = new Thread(reader);
            readerthread.start();

            if (sender.hasStream() && reader.hasStream()) {
                this.connected = true;
            }

        } catch (IOException e) {
            System.out.println(e);
        }

        loop();
    }

    public void loop() throws IOException {
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
    
    public void close() throws IOException{
        connected = false;
        socket.close();
        outputStream.close();
    }

    public boolean getConnected()
    {
        return connected;
    }
}
