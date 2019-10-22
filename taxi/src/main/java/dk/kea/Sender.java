package dk.kea;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class Sender implements Runnable
{
    private ServerHandler client;
    private DataOutputStream output = null;

    public Sender(ServerHandler client, Socket socket) throws IOException
    {
        this.client = client;
        this.output = new DataOutputStream(socket.getOutputStream());
    }

    public void send(String msg) throws SocketException, IOException {
        output.writeUTF(msg);
    }

    public boolean hasStream()
    {
        return null != output;
    }

    @Override
    public void run() {
        while(client.getConnected())
        {

        }
    }
    
}