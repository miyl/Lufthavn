package dk.kea.management;

import dk.kea.Models.Flights;
import dk.kea.dbconnect.DBConnect;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class Airplanes{
    DBConnect dbConnect;

    public Airplanes() {
    }


    public void getPlanes(){
        dbConnect = new DBConnect();
        List<Flights> flights = new ArrayList<Flights>();
        try {
            Statement stmt = dbConnect.getConnection().createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM Fly");
            while(rs.next()){
                Flights flight = new Flights();
                flight.setId(rs.getInt("fly_id"));
                flight.setName(rs.getString("name"));
                flight.setModel(rs.getString("model"));
                flight.setFlightSize(rs.getString("flightSize"));
                flight.setLuftSelskab(rs.getString("luftSelskab"));
                flight.setDeparture(rs.getDate("departure"));
                flight.setArrival(rs.getDate("arrival"));
                flight.setGate(rs.getObject("gate"));
                flight.setPriorityNumber(rs.getInt("priorityNumber"));
                flight.setStandPlads(rs.getInt("standPlads"));
                flights.add(flight);


            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }


}
