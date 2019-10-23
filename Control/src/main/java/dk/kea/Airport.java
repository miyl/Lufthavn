package dk.kea;

public class Airport {

    public static void main(String args[]) {

        // Start server thread.
        Server server = new Server(5000);
        Thread t = new Thread(server);
        t.start();

        // Do something
        // ...
        //
        // If this should be refactored to somewhere else feel free to do so
        //
        // FETCH data from the database for the clients
        //var fdh = new FlightDbHandler();
        //var gdh = new Airplanes();
        
        // GET the client handlers
        var taxi = server.getTaxi();
        var luggage = server.getLuggage();
        var clean = server.getClean();
        var fuel = server.getFuel();

        // Main airport flow
        // taxi.

    }
}
