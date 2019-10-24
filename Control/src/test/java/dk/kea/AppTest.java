package dk.kea;

import dk.kea.management.DBConnectTest;
import dk.kea.models.Gate;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest {


    @Test
    void testGates() {

        DBConnectTest dbConnect = new DBConnectTest();
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
        assertEquals(gates.get(0).getNumber(), 1);
        assertEquals(gates.get(1).getNumber(), 2);
        assertEquals(gates.get(0).getGateSize(), "lille");
        assertEquals(gates.get(11).getGateSize(), "stor");



    }

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue( true );
    }




}
