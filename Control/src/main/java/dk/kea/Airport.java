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

        System.out.print(getQueue().get(0));
        for(int current = 0 ; current < getQueue().size(); current += 1){
            var item = getQueue().get(current);
            String next = getQueue().size() > current + 1 ? getQueue().get(current + 1) : "FINISH\n\n";
            switch(item){
                case "TAXI":
                    if(t_step == 1)
                    {
                        // do something different
                    }
                    System.out.printf(" -> " + next);
                    taxi.sendList(flights);
                    flights = taxi.readList();
                    t_step++;
                    break;
                case "LUGGAGE":
                    if(l_step == 1)
                    {
                        // do something different
                    }
                    System.out.printf(" -> " + next);
                    luggage.sendList(flights);
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
        flights.forEach(plane -> System.out.print("        [" + plane.getId() + ", " + plane.getExpectedDeparture().getTime() + "]\n"));
        System.out.println();

    }
}
