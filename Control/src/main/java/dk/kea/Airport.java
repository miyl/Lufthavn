package dk.kea;

import java.util.List;

import dk.kea.management.FlightDbHandler;
import dk.kea.models.Flight;

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
        while(true) 
        {
            airportFlow();
        }
    }

    public static void airportFlow() {

        // If this should be refactored to somewhere else feel free to do so
        //
        // FETCH data from the database for the clients
        List<Flight> flights = new FlightDbHandler().fetchAll();
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
        
        // TODO/Thoughts: Spawn it in a thread when/if two or more can happen simultaneously, and check that both have finished before continuing?
        // send them the planes gradually? With randomisation?

        List<Flight> temp = flights;

        // Taxi in
        if(taxi != null)
        {
            taxi.send(flights);
            temp = taxi.readList();
        }
    
        // Passengers out
        // Handled by the server currently, or?
        
        // Luggage out
        if(luggage != null)
        {
            luggage.send(temp);
            temp = luggage.readList();
        }
    
        // Refuel
        if(fuel != null)
        {
            fuel.send(temp);
            temp = fuel.readList();
        }
    
        // Clean
        if(clean != null)
        {
            clean.send(temp);
            temp = clean.readList();
        }
    
        // Luggage in
        if(luggage != null)
        {
            luggage.send(temp);
            temp = luggage.readList();
        }
    
        // Passengers in
        // Handled by the server currently, or?
    
        // Taxi to departure
        if(taxi != null)
        {
            taxi.send(temp);
            temp = taxi.readList();
        }
        //   // After dealing with the plane taxi client is free to "taxi til og fra ventepladser"
        //   // The plane has departed and can be logged for time of departure, deleted and/or whatever
        //
        //   //Personel move among neighboring gates
        //   //Personel move among own terminal
        //   //Personel move across terminals

        System.out.println("Ran through the queue with:");
        temp.forEach(plane -> System.out.print("      [" + plane.getName() + ", " + plane.getArrival() + "]\n"));
        System.out.println();

    }
}
