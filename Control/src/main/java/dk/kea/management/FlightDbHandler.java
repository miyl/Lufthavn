package dk.kea.management;

import dk.kea.Crud;
import dk.kea.models.Flight;
import dk.kea.models.Gate;
import dk.kea.dbconnect.DBConnect;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.List;


public class FlightDbHandler implements Crud<Flight> {
    DBConnect dbConnect;

    @Override
    public List<Flight> fetchAll(){
        List<Flight> flights = new ArrayList<Flight>();
        try {
            dbConnect = new DBConnect();
            Statement stmt = dbConnect.getConnection().createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM Fly inner join gate on Fly.gate = gate.number");
            while(rs.next()){

                Flight flight = new Flight();
                Gate gate = new Gate();
                flight.setId(rs.getInt("fly_id"));
                flight.setName(rs.getString("name"));
                flight.setModel(rs.getString("model"));
                flight.setFlightSize(rs.getString("flightSize"));
                flight.setLuftSelskab(rs.getString("luftSelskab"));
                flight.setDeparture(rs.getTimestamp("departure"));
                flight.setArrival(rs.getTimestamp("arrival"));
                flight.setGate(gate);
                flight.setPriorityNumber(rs.getInt("priorityNumber"));
                flight.setExpectedDeparture(rs.getTimestamp("expectedDeparture"));
            
                gate.setNumber(rs.getInt("number"));
                gate.setGateSize(rs.getString("gateSize"));
                gate.setTerminal(rs.getString("terminal"));

                flights.add(flight);
            }
            dbConnect.getConnection().close();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return flights;
    }
    public void addObject(String model, String flightSize, String name, int gate, int priorityNumber, Timestamp arrival, Timestamp departure, String luftSelskab, Timestamp expectedDeparture){
        try{ 
            dbConnect = new DBConnect();
        
            Statement stmt = dbConnect.getConnection().createStatement();
            stmt.executeUpdate("INSERT INTO Fly (model, standPlads, flightSize, name, gate, priorityNumber, arrival, departure, luftSelskab, expectedDeparture)" +
                    "VALUES('"+model+"', null, '"+flightSize+"', '"+name+"', "+gate+", "+priorityNumber+", "+arrival+", "+departure+", '"+luftSelskab+"', "+expectedDeparture+")");
            dbConnect.getConnection().close();
        }catch (Exception e){

        }
    }


    @Override
    public void addObject(Flight flight) {
        String model = flight.getModel();
        String flightSize = flight.getFlightSize();
        String name = flight.getName();
        int gate = flight.getGate().getNumber();
        int priorityNumber = flight.getPriorityNumber();
        Timestamp arrival = flight.getArrival();
        Timestamp departure = flight.getDeparture();
        String luftSelskab = flight.getLuftSelskab();
        Timestamp expectedDeparture = flight.getExpectedDeparture();

        try{
            dbConnect = new DBConnect();
        
            Statement stmt = dbConnect.getConnection().createStatement();
            stmt.executeUpdate("INSERT INTO Fly (model, standPlads, flightSize, name, gate, priorityNumber, arrival, departure, luftSelskab, expectedDeparture)" +
                    "VALUES('"+model+"', null, '"+flightSize+"', '"+name+"', "+gate+", "+priorityNumber+", "+arrival+", "+departure+", '"+luftSelskab+"', "+expectedDeparture+")");
            dbConnect.getConnection().close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public List findById(int id) {
        return null;
    }

    public void updateObjects(List<Flight> flights){
        flights.forEach(flight -> {
            updateObject(flight);
        });
    }

    @Override
    public void updateObject(Flight flight) {
        int id = flight.getId();
        String model = flight.getModel();
        String flightSize = flight.getFlightSize();
        String name = flight.getName();
        int gate = flight.getGate().getNumber();
        int priorityNumber = flight.getPriorityNumber();
        Timestamp arrival = flight.getArrival();
        Timestamp departure = flight.getDeparture();
        String luftSelskab = flight.getLuftSelskab();
        Timestamp expectedDeparture = flight.getExpectedDeparture();

        try{
            dbConnect = new DBConnect();
        
            Statement stmt = dbConnect.getConnection().createStatement();
            stmt.executeUpdate("UPDATE Fly SET "+
                    "model = '"+model+"', "+
                    "flightSize = '"+flightSize+"', "+
                    "name = '"+name+"', "+
                    "gate = "+gate+", "+
                    "priorityNumber = "+priorityNumber+", "+
                    "arrival = '"+arrival+"', "+
                    "departure = '"+departure+"', "+
                    "luftSelskab = '"+luftSelskab+"', "+
                    "expectedDeparture = '"+expectedDeparture+"' "+
                    "WHERE fly_id="+id);

            dbConnect.getConnection().close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
