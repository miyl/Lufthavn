package dk.kea;

import dk.kea.shared.*;

public class Airport {

    public static void main(String args[]) {

        // Start server thread.
        Server server = new Server(5000);
        Thread t = new Thread(server);
        t.start();

        // Do something
        // ...

        /*while (true) {
            if (server.isAllConnected())
            {
                server.getTaxi().sendPlane(new Flights());
                break;
            }
        }*/
    }

}
