package dk.kea;

import java.util.List;

public class Airport {

    private static Server server = null;

    public static void main(String args[]) {

        // Start server thread.
        server = new Server(5000);
        Thread t = new Thread(server);
        t.start();

        // Do something
        // ...
        //
    }

    public static void airportFlow() {

        // If this should be refactored to somewhere else feel free to do so
        //
        // FETCH data from the database for the clients
        //List<Flight> flights = new FlightDbHandler().getFlights();
        //List<Gate> gates = new GateDbHandler().getGates();
        
        // GET the client handlers
        var taxi = server.getTaxi();
        var luggage = server.getLuggage();
        var clean = server.getClean();
        var fuel = server.getFuel();

        // Main airport flow
        // Send gates to all who need it
        // taxi.send(gates);
        // luggage.send(gates);
        // clean.send(gates);
        // fuel.send(gates);
        //
        // TODO/Thoughts: Spawn it in a thread when/if two or more can happen simultaneously, and check that both have finished before continuing?
        // send them the planes gradually? With randomisation?
        // Flight temp = null;
        // for (var f : flights) {
        //
        //   // Taxi in
        //   taxi.send(f);
        //   temp = taxi.receive(f);
        //
        //   // Passengers out
        //   // Handled by the server currently, or?
        // }
        //   // Luggage out
        //   luggage.send(temp);
        //   temp = luggage.receive();
        //
        //   // Refuel
        //   fuel.send(temp);
        //   temp = fuel.receive();
        //
        //   // Clean
        //   clean.send(temp);
        //   temp = clean.receive();
        //
        //   // Luggage in
        //   luggage.send(temp);
        //   temp = luggage.receive();
        //
        //   // Passengers in
        //   // Handled by the server currently, or?
        //
        //   // Taxi to departure
        //   taxi.send(temp);
        //   temp = taxi.receive();
        //   // After dealing with the plane taxi client is free to "taxi til og fra ventepladser"
        //   // The plane has departed and can be logged for time of departure, deleted and/or whatever
        //
        //   //Personel move among neighboring gates
        //   //Personel move among own terminal
        //   //Personel move across terminals

    }
}
