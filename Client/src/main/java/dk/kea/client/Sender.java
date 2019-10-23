package dk.kea.client;

import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Sender implements Runnable
{
    private ServerHandler client;
    private ObjectOutputStream output = null;

    public Sender(ServerHandler client, Socket socket) throws IOException
    {
        this.client = client;
        this.output = new ObjectOutputStream(socket.getOutputStream());
    }

    public void send(String msg){
        try {
            output.writeUTF(msg);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public boolean hasStream()
    {
        return null != output;
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
            output.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}