package dk.kea.client;

import java.io.IOException;
import java.io.ObjectInputStream;
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

    public Object read(){
        try {
            return input.readObject();
        } catch (IOException e) {
            
        } catch (ClassNotFoundException e) {
            System.out.print("hej");
        }
        return null;
    }

    public boolean hasStream()
    {
        return null != input;
    }
    
}