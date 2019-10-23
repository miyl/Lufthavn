package main.java.dk.kea.shared;
import java.util.ArrayList;

//Klasse til boarding af passengers. Fra høj til lav prioritet
public class Boarding {
    private ArrayList<String> passengers;
    private int capacity;

    public Boarding(){
    }

    public Boarding(int numSeats){
        capacity = numSeats;
        passengers = new ArrayList<String>();
    }

    public void boardPassenger(String name, boolean priority)  {
        if (!isFull()){
            if (priority){
                passengers.add(0, name);
            } else {
                passengers.add(name);
            }
        }
    }

    public boolean isFull() {
        return capacity == passengers.size();
    }

    public boolean isEmpty(){
        return passengers.isEmpty();
    }

    public String unboardPassenger(){
        if(!isEmpty()) {
            return passengers.remove(0);
        }
        return null;
    }

}
