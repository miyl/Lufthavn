package dk.kea.management;

import dk.kea.dbconnect.DBConnect;
import dk.kea.models.Gate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GateDbHandler {
    DBConnect dbConnect;

    public List<Gate> getGates(){
        dbConnect = new DBConnect();
        List<Gate> gates = new ArrayList<Gate>();

        try {
            Statement stmt = dbConnect.getConnection().createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM gate");
            while(rs.next()){

                Gate gate = new Gate();
                gate.setNumber(rs.getInt("number"));
                gate.setGateSize(rs.getString("gateSize"));
                gate.setTerminal(rs.getString("terminal"));

                gates.add(gate);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return gates;
    }
}
