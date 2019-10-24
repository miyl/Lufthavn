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
            if(server.isOneConnected())
            {
                airportFlow();
            }
        }
    }

    public static void airportFlow() {

        System.out.println("Running queue:");

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

        var l_step = 0;
        var t_step = 0;

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
        if(taxi != null && t_step == 0)
        {
            System.out.printf("Taxi -> ");
            taxi.send(flights);
            temp = taxi.readList();
            t_step++;
        }
    
        // Passengers out
        // Handled by the server currently, or?
        
        // Luggage out
        if(luggage != null && l_step == 0)
        {
            System.out.printf("Luggage -> ");
            luggage.send(temp);
            temp = luggage.readList();
            l_step++;
        }
    
        // Refuel
        if(fuel != null)
        {
            System.out.printf("Fuel -> ");
            fuel.send(temp);
            temp = fuel.readList();
        }
    
        // Clean
        if(clean != null)
        {
            System.out.printf("Clean -> ");
            clean.send(temp);
            temp = clean.readList();
        }
    
        // Luggage in
        if(luggage != null && l_step == 1)
        {
            System.out.printf("Luggage -> ");
            luggage.send(temp);
            temp = luggage.readList();
            l_step = 0;
        }
    
        // Passengers in
        // Handled by the server currently, or?
    
        // Taxi to departure
        if(taxi != null && t_step == 1)
        {
            taxi.send(temp);
            temp = taxi.readList();
            t_step = 0;
        }
        //   // After dealing with the plane taxi client is free to "taxi til og fra ventepladser"
        //   // The plane has departed and can be logged for time of departure, deleted and/or whatever
        //
        //   //Personel move among neighboring gates
        //   //Personel move among own terminal
        //   //Personel move across terminals

        System.out.printf("Done\n\n");

        System.out.println("Ran through the queue with:");
        temp.forEach(plane -> System.out.print("      [" + plane.getName() + ", " + plane.getExpectedDeparture() + "]\n"));
        System.out.println();

    }
}
