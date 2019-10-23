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
        try {
            Statement stmt = dbConnect.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Fly");
            while(rs.next()){
                System.out.println(rs.getString(2) + ";" + rs.getString(3));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    List<Flights> flights = new ArrayList<Flights>();
}
