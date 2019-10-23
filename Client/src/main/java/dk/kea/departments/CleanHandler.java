package dk.kea.departments;
import dk.kea.client.ServerHandler;

import java.net.Socket;

public class CleanHandler extends ServerHandler {
    public CleanHandler(Socket socket){
        super(socket);
    }
}
