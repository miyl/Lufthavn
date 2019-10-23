package dk.kea.management;

import dk.kea.models.Flight;
import dk.kea.models.Gate;
import dk.kea.dbconnect.DBConnect;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class FlightDbHandler{
    DBConnect dbConnect;

    public List<Flight> getFlights(){
        dbConnect = new DBConnect();
        List<Flight> flights = new ArrayList<Flight>();
        try {
            Statement stmt = dbConnect.getConnection().createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM Fly");
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

                ResultSet rs_ = stmt.executeQuery("SELECT * FROM Gate WHERE id = " + flight.getId());
                while(rs_.next()){
                    gate.setNumber(rs_.getInt("number"));
                    gate.setGateSize(rs_.getString("gateSize"));
                    gate.setTerminal(rs_.getString("terminal"));
                }
                flights.add(flight);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return flights;
    }


}
