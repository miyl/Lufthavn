package dk.kea.client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import dk.kea.shared.Flight;

public class Sender implements Runnable
{
    private ServerHandler client;
    private ObjectOutputStream output = null;

    public Sender(ServerHandler client, Socket socket, ObjectOutputStream objectOutputStream) throws IOException
    {
        this.client = client;
        this.output = objectOutputStream;
    }

    public void send(String msg, Boolean utf){
        try {
            if(utf)
            {
                output.writeUTF(msg);
            } else 
            {
                output.writeObject(msg);
            }
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendPlane(Flight airplane){
        try {
            output.writeObject(airplane);
            output.flush();
        } catch (IOException e) {
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
            e.printStackTrace();
        }
    }
    
}
