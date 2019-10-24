package dk.kea.management;

import dk.kea.Crud;
import dk.kea.models.Flight;
import dk.kea.models.Gate;
import dk.kea.dbconnect.DBConnect;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class FlightDbHandler implements Crud<Flight> {
    DBConnect dbConnect;

    @Override
    public List<Flight> fetchAll(){
        dbConnect = new DBConnect();
        List<Flight> flights = new ArrayList<Flight>();
        try {
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
                flight.setDeparture(rs.getDate("departure"));
                flight.setArrival(rs.getDate("arrival"));
                flight.setGate(gate);
                flight.setPriorityNumber(rs.getInt("priorityNumber"));
                flight.setStandPlads(rs.getInt("standPlads"));
                //flight.setExpectedDeparture(rs.getDate("expectedDeparture"));
            
                gate.setNumber(rs.getInt("number"));
                gate.setGateSize(rs.getString("gateSize"));
                gate.setTerminal(rs.getString("terminal"));

                flights.add(flight);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return flights;
    }
    public void addObject(String model, String flightSize, String name, int gate, int priorityNumber, LocalDateTime arrival, LocalDateTime departure, String luftSelskab, LocalDateTime expectedDeparture){
        dbConnect = new DBConnect();
        try{
            Statement stmt = dbConnect.getConnection().createStatement();
            stmt.executeUpdate("INSERT INTO Fly (model, standPlads, flightSize, name, gate, priorityNumber, arrival, departure, luftSelskab, expectedDeparture)" +
                    "VALUES('"+model+"', null, '"+flightSize+"', '"+name+"', "+gate+", "+priorityNumber+", "+arrival+", "+departure+", '"+luftSelskab+"', "+expectedDeparture+")");

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
        Date arrival = flight.getArrival();
        Date departure = flight.getDeparture();
        String luftSelskab = flight.getLuftSelskab();
        Date expectedDeparture = flight.getExpectedDeparture();


        dbConnect = new DBConnect();
        try{
            Statement stmt = dbConnect.getConnection().createStatement();
            stmt.executeUpdate("INSERT INTO Fly (model, standPlads, flightSize, name, gate, priorityNumber, arrival, departure, luftSelskab, expectedDeparture)" +
                    "VALUES('"+model+"', null, '"+flightSize+"', '"+name+"', "+gate+", "+priorityNumber+", "+arrival+", "+departure+", '"+luftSelskab+"', "+expectedDeparture+")");

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

    @Override
    public void updateObject(Flight flight) {
        int id = flight.getId();
        String model = flight.getModel();
        String flightSize = flight.getFlightSize();
        String name = flight.getName();
        int gate = flight.getGate().getNumber();
        int priorityNumber = flight.getPriorityNumber();
        Date arrival = flight.getArrival();
        Date departure = flight.getDeparture();
        String luftSelskab = flight.getLuftSelskab();
        Date expectedDeparture = flight.getExpectedDeparture();

        dbConnect = new DBConnect();
        try{
            Statement stmt = dbConnect.getConnection().createStatement();
            stmt.executeUpdate("UPDATE Fly SET"+
                    "model = '"+model+"', "+
                    "flightSize = '"+flightSize+"', "+
                    "name = '"+name+"', "+
                    "gate = "+gate+", "+
                    "priorityNumber = "+priorityNumber+", "+
                    "arrival = "+arrival+", "+
                    "departure = "+departure+", "+
                    "luftSelskab = '"+luftSelskab+"', "+
                    "expectedDeparture = "+expectedDeparture+" "+
                    "WHERE fly_id="+id);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
