package dk.kea;

public class Airport {

    public static void main(String args[]) {

        // Start server thread.
        Server server = new Server(5000);
        Thread t = new Thread(server);
        t.start();

        // Do something
        // ...
    }

}
