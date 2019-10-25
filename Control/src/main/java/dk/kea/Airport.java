package dk.kea;

import java.util.ArrayList;
import java.util.List;

import dk.kea.management.FlightDbHandler;
import dk.kea.models.Flight;
import static dk.kea.Queue.getQueue;

public class Airport {

    public static Server server = null;
    private static FlightDbHandler flightDbHandler;

    public static void main(String args[]) {

        // Start server thread.
        server = new Server(5000);
        Thread t = new Thread(server);
        t.start();

        flightDbHandler = new FlightDbHandler();


        // Do something
        // ...
        //
        while(true) 
        {
            if(server.isAllConnected())
            {
                airportFlow();
            }
        }

    }

    public static void airportFlow() {
        

        System.out.println("Running qkd,asasueue:".toUpperCase());

        // If this should be refactored to somewhere else feel free to do so
        //
        // FETCH data from the database for the clients
        List<Flight> flights = flightDbHandler.fetchAll();

        flights.forEach(flight -> {
            flight.setExpectedDeparture(flight.getArrival());
        });

        flightDbHandler.updateObjects(flights);
        //List<Gate> gates = new GateDbHandler().getGates();

        // GET the client handlers
        var taxi = server.getTaxi();
        var luggage = server.getLuggage();
        var clean = server.getClean();
        var fuel = server.getFuel();

        var l_step = 0;
        var t_step = 0;

        var queue = getQueue();

        System.out.printf("[INFO]:    CURRENT QUEUE\n           ");
        queue.forEach(item -> System.out.printf(item + " "));
        System.out.printf("\n\n[RUNNING]: ");

        System.out.print(queue.get(0));
        for(int current = 0 ; current < queue.size(); current += 1){
            //flights.forEach(plane -> System.out.print("        [" + plane.getId() + ", " + plane.getExpectedDeparture() + "]\n"));
            var item = queue.get(current);
            String next = queue.size() > current + 1 ? queue.get(current + 1) : "FINISH\n\n";
            System.out.printf(" -> " + next);
            switch(item){
                case "TAXI":
                    taxi.sendList(flights);
                    //taxi.sendNumber(Integer.toString(t_step));
                    flights = taxi.readList();
                    break;
                case "LUGGAGE":
                    luggage.sendList(flights);
                    //taxi.sendNumber(Integer.toString(l_step));
                    flights = luggage.readList();
                    break;
                case "CLEAN":
                    clean.sendList(flights);
                    flights = clean.readList();
                    break;
                case "FUEL":
                    fuel.sendList(flights);
                    flights = fuel.readList();
                    break;
            }
        }


        flightDbHandler.updateObjects(flights);

        System.out.println("[DONE]: Ran through the queue with:".toUpperCase());
        flights.forEach(plane -> System.out.print("        [" + plane.getId() + ", " + plane.getExpectedDeparture() + "]\n"));
        System.out.println();

    }
}
