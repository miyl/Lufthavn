package dk.kea.management;

import dk.kea.dbconnect.DBConnect;
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
 *         manageUser.create("Hans", "123456", 2);
 *
 *         //slet bruger
 *         //manageUser.delete("hans");
 *
 *         //udskriv brugere
 *         manageUser.print();
 *
 *         //login - returnere en boolean -> true hvis password er korrekt
 *         manageUser.chkCredentials("Hans", "123456");
 *
 *         //update mangler
 *
 **/

public class Users {
    DBConnect dbConnect;
    //randomword tilføjes til plaintext password når password hashes og checkes.
    final String randomWord = "Aerobics";
    public Users() {
    }

    //Opret ny bruger
    //TODO: mangler email adresse (hardcoded i insert string pt.)
    public void create(String username, String plaintext_pw, int hold_id){
        //hash password
        String randomWord = "Aerobics";
        String hashedPassword = BCrypt.hashpw((plaintext_pw + randomWord), BCrypt.gensalt());

        //tilføj bruger til db
        dbConnect = new DBConnect();
        try {
            Statement stmt = dbConnect.getConnection().createStatement();
            stmt.executeUpdate("insert into Personale_login (brugernavn, kodeord, `e-mail`, hold_id) VALUES ('"+username+"', '"+hashedPassword+"', '"+"emailadresshere"+"', "+hold_id+");");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void delete(String username){
        dbConnect = new DBConnect();
        try {
            Statement stmt = dbConnect.getConnection().createStatement();
            stmt.executeUpdate("delete from Personale_login where brugernavn='"+username+"';");
        }catch (SQLException e){
            e.printStackTrace();
        }
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

}
