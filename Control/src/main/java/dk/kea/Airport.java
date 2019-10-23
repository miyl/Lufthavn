package dk.kea;

import dk.kea.management.Users;

public class Airport {

    public static void main(String args[]) {

        // Start server thread.
        Server server = new Server(5000);
        Thread t = new Thread(server);
        t.start();

        // Do something
        // ...

        Users users = new Users();
        //users.create("bruger3", "123456", 2);
    }

}
