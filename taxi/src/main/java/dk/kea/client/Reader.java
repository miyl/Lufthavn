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
            try {
                read();
            } catch (SocketException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void read() throws SocketException, IOException{
        System.out.println(input.readUTF());
    }

    public boolean hasStream()
    {
        return null != input;
    }
    
}