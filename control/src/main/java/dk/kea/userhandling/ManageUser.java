package dk.kea.manageusers;

import dk.kea.dbconnect.DBConnect;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ManageUser {

    ArrayList<User> userList = new ArrayList<>();
    public ManageUser() {
    }

    public void add(String username, String password, int hold_id){
        User user = new User(username, password, hold_id);
        //add to db.
        userList.add(user);
    }
    public void delete(String username){
        //remove from db where username equals username in db
    }
    public void print(){
        DBConnect dbConnect = new DBConnect();

        try {
            Statement stmt = dbConnect.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Personale_login");
            while(rs.next()){
                System.out.println(rs.getString(2));
                System.out.println(rs.getString(3));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void update(){

    }
    public boolean chkCredentials(String username, String password){ ;
        for(int i = 0; i < userList.size(); i++){
            if(userList.get(i).getUsername().equals(username)){
                if(BCrypt.checkpw(password, userList.get(i).getHashedPassword())){
                    //password correct
                    System.out.println("Login success");
                    return true;
                }
            }
        }
        System.out.println("User not found / Password incorrect");
        return false;
    }
}
