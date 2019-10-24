package dk.kea.management;

import dk.kea.Crud;
import dk.kea.dbconnect.DBConnect;
import dk.kea.models.Gate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GateDbHandler implements Crud<Gate> {
    DBConnect dbConnect;
    @Override
    public List<Gate> fetchAll(){
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

    @Override
    public void addObject(Gate gate) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public List findById(int id) {
        return null;
    }

    @Override
    public void updateObject(Gate gate) {

    }
}
