package dk.kea.client;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.net.Socket;

public class Reader implements Runnable
{
    private ServerHandler client;
    private ObjectInputStream input = null;

    public Reader(ServerHandler client, Socket socket) throws IOException
    {
        this.client = client;
        this.input = new ObjectInputStream(socket.getInputStream());;
    }

    @Override
    public void run() {
        while(client.isConnected()) 
        {
            
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

    public String read(){
        try {
            return input.readUTF();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public boolean hasStream()
    {
        return null != input;
    }
    
}