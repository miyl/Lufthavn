package dk.kea.shared;
import java.util.ArrayList;

//Klasse til boarding af passengers. Fra høj til lav prioritet
public class Boarding {
    private ArrayList<String> passengers;
    private int capacity;
    private String flightSize;

    public Boarding(){
    }

    public Boarding(int numSeats, String flightSize){
        capacity = numSeats;
        passengers = new ArrayList<String>();
        this.flightSize = flightSize;
    }

    public void boardPassenger(String name) {
        if (!isFull()){
                passengers.add(0, name);
            }
        }

    public String getFlightSize() {
        return flightSize;
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
