package dk.kea.handlers;

import dk.kea.models.Flight;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.List;

public class CleaningDepartmentHandler extends DepartmentHandler {

    public CleaningDepartmentHandler(Socket socket, ObjectInputStream input, ObjectOutputStream output) {
        super(socket, input, output, "Cleaning");
    }

    @Override
    public void run(){
        try{
            while (isRunning){

            }

            input.close();
            output.close();
            socket.close();

        } catch (IOException e){
            System.out.println("** " + name + " thread exited **");

            isRunning = false;
        }

    }

    public void sendSingle(Serializable serialized) {
        try {
            output.writeObject(serialized);
            output.flush();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public List<Flight> readList() {
        try {
            return(List<Flight>) input.readObject();
        } catch (ClassNotFoundException | IOException e) {
            return null;
        }

    }

    public void sendList (List<? extends Serializable> SerializedList){
        try {

            output.writeObject(SerializedList);
            output.flush();

        } catch (IOException e){
            System.err.println(e);
        }
    }

    public void sendNumber(String number){
        try{
            output.writeObject((String) number);
            output.flush();
        } catch (IOException e){
            System.err.println(e);
        }
    }

    public Flight readSingle() {
        try {
            return (Flight) input.readObject();
        } catch (ClassNotFoundException | IOException e) {
            return null;
        }
    }


}