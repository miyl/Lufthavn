package dk.kea.client;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class Reader implements Runnable
{
    private ServerHandler client;
    private DataInputStream input = null;

    public Reader(ServerHandler client, Socket socket) throws IOException
    {
        this.client = client;
        this.input = new DataInputStream(new BufferedInputStream(socket.getInputStream()));;
    }

    @Override
    public void run() {
        while(client.getConnected()) 
        {
            read();
        }
        close();
    }

    public void close(){
        try {
            input.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void read(){
        try {
            System.out.println(input.readUTF());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public boolean hasStream()
    {
        return null != input;
    }
    
}