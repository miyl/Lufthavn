package dk.kea.management;

import dk.kea.dbconnect.DBConnect;
import dk.kea.models.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * CRUD på tabellen Personale_login
 *
 *         //Eksempler:
 *
 *         //opret bruger
 *         userDbHandler.create(new User("User1", "User1", 2, "t@dk.dk"));
 *
 *         //slet bruger
 *         //userDbHandler.delete("hans");
 *
 *         //udskriv brugere
 *         userDbHandler.print();
 *
 *         //login - returnere en boolean -> true hvis password er korrekt
 *         userDbHandler.chkCredentials("Hans", "123456");
 *
 *         //update mangler
 *
 **/

public class UserDbHandler {
    DBConnect dbConnect;
    //randomword tilføjes sættes sammen med plaintext password før password hashes og checkes.
    final String randomWord = "Aerobics";
    public UserDbHandler() {
    }

    //Opret ny bruger

    public void create(User user){
        //hash password
        String hashedPassword = BCrypt.hashpw((user.getPlaintext_pw() + randomWord), BCrypt.gensalt());

        //tilføj bruger til db
        dbConnect = new DBConnect();
        try {
            Statement stmt = dbConnect.getConnection().createStatement();
            stmt.executeUpdate("insert into Personale_login (brugernavn, kodeord, `e-mail`, hold_id) VALUES ('"+user.getUsername()+"', '"+hashedPassword+"', '"+user.getEmailadress()+"', "+user.getHold_id()+");");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public int delete(String username){
        dbConnect = new DBConnect();
        try {
            Statement stmt = dbConnect.getConnection().createStatement();
            return stmt.executeUpdate("delete from Personale_login where brugernavn='"+username+"';");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }
    public void print(){
        dbConnect = new DBConnect();
        try {
            Statement stmt = dbConnect.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Personale_login");
            while(rs.next()){
                System.out.println(rs.getString(2) + ";" + rs.getString(3));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    //TODO: Update mangler
    public void update(){

    }

    //Metoden bruges når en person logger på.
    //username og password checkes.
    public boolean chkCredentials(String username, String plaintext_pw){ ;
    dbConnect = new DBConnect();
        try {
            Statement stmt = dbConnect.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("select kodeord from Personale_login where brugernavn='"+username+"';");

            if(rs.next()){
                String hashedPassword = rs.getString(1);
                if(BCrypt.checkpw((plaintext_pw + randomWord), hashedPassword)){
                    //password correct
                    return true;
                }
                else {
                    //password incorrect
                    return false;
                }
            }
            else {
                //no result found?
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        //something went wrong
        return false;
    }

    public String getAfdeling(String username){
        dbConnect = new DBConnect();
        try {
            Statement stmt = dbConnect.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT hold_navn FROM Personale INNER JOIN Personale_login on Personale_login.hold_id = Personale.hold_id where brugernavn='"+username+"';");
            if(rs.next()){
                return rs.getString(1);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return "";
    }
}
