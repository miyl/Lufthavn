package dk.kea.departments;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import dk.kea.client.ServerHandler;
import dk.kea.models.Flight;
import java.net.Socket;
import java.util.List;

public class TaxiHandler extends ServerHandler
{

    public TaxiHandler(Socket socket, ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream){
        super( socket, objectInputStream, objectOutputStream);
    }

    public void start(){

        while (isConnected()) {

            List<Flight> flightArray = getFlightList();
            if (!flightArray.isEmpty()){



            }



        }
    }
}
