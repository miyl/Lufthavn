package dk.kea;

import dk.kea.dbconnect.DBConnect;
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

        DBConnect dbConnect = new DBConnect();
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
        assertEquals(1, gates.get(0).getNumber());
        assertEquals(2, gates.get(1).getNumber());
        assertEquals("lille", gates.get(0).getGateSize());
        assertEquals("stor", gates.get(11).getGateSize());



    }

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue( true );
    }




}
