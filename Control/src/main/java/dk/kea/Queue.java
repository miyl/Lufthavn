package dk.kea;

import java.util.ArrayList;
import java.util.List;

public class Queue
{
    private static List<String> queue = new ArrayList<>();
    
    public Queue()
    {

    }

    public static List<String> getQueue()
    {
        updateQueue(getActiveList());
        return queue;
    }

    public static List<String> getActiveList()
    {
        List<String> active = new ArrayList<>();

        if(Airport.server.getTaxi() != null){
            active.add("TAXI");
        }
        if(Airport.server.getLuggage() != null){
            active.add("LUGGAGE");
        }
        if(Airport.server.getFuel() != null){
            active.add("FUEL");
        }
        if(Airport.server.getClean() != null){
            active.add("CLEAN");
        }

        return active;
    }

    public static void updateQueue(List<String> activeList)
    {
        queue.clear();
        activeList.forEach(item -> {
            switch(item)
            {
                case "TAXI":
                    queue.add(item);
                    queue.add(item);
                    break;
                case "LUGGAGE":
                    if(activeList.indexOf("TAXI") != -1)
                    {
                        queue.add(activeList.indexOf("TAXI") + 1, item);
                        queue.add(activeList.indexOf(item) + 1, item);
                    } else 
                    {
                        queue.add(item);
                        queue.add(item);
                    }
                    break;
                case "FUEL":
                    if(activeList.indexOf("LUGGAGE") != -1)
                    {
                        queue.add(activeList.indexOf("LUGGAGE") + 1, item);
                    } else if(activeList.indexOf("TAXI") != -1)
                    {
                        queue.add(activeList.indexOf("TAXI") + 1, item);
                    } else 
                    {
                        queue.add(item);
                    }
                    break;
                case "CLEAN":
                    if(activeList.indexOf("FUEL") != -1)
                    {
                        queue.add(activeList.indexOf("FUEL") + 1, item);
                    } 
                    else if(activeList.indexOf("LUGGAGE") != -1)
                    {
                        queue.add(activeList.indexOf("LUGGAGE") + 1, item);
                    } 
                    else if(activeList.indexOf("TAXI") != -1)
                    {
                        queue.add(activeList.indexOf("TAXI") + 1, item);
                    } 
                    else 
                    {
                        queue.add(item);
                    }
                    break;
            } 
        });
    }
}