package dk.kea;

import java.util.ArrayList;
import java.util.List;

import dk.kea.management.FlightDbHandler;
import dk.kea.models.Flight;
import static dk.kea.Queue.getQueue;

public class Airport {

    public static Server server = null;

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
        

        System.out.println("Running queue:".toUpperCase());

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

        var queue = getQueue();

        System.out.printf("[INFO]:    CURRENT QUEUE\n           ");
        queue.forEach(item -> System.out.printf(item + " "));
        System.out.printf("\n\n[RUNNING]: ");

        System.out.print(queue.get(0));
        for(int current = 0 ; current < queue.size(); current += 1){
            var item = queue.get(current);
            String next = queue.size() > current + 1 ? queue.get(current + 1) : "FINISH\n\n";
            switch(item){
                case "TAXI":
                    System.out.printf(" -> " + next);
                    taxi.sendList(flights);
                    taxi.sendNumber(Integer.toString(t_step));
                    flights = taxi.readList();
                    t_step++;
                    break;
                case "LUGGAGE":
                    System.out.printf(" -> " + next);
                    luggage.sendList(flights);
                    taxi.sendNumber(Integer.toString(l_step));
                    flights = luggage.readList();
                    l_step++;
                    break;
                case "CLEAN":
                    System.out.printf(" -> " + next);
                    clean.sendList(flights);
                    flights = clean.readList();
                    break;
                case "FUEL":
                    System.out.printf(" -> " + next);
                    fuel.sendList(flights);
                    flights = fuel.readList();
                    break;
            }
        }



        System.out.println("[DONE]: Ran through the queue with:".toUpperCase());
        flights.forEach(plane -> System.out.print("        [" + plane.getId() + ", " + plane.getExpectedDeparture() + "]\n"));
        System.out.println();

    }
}
