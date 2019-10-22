package dk.kea.manageusers;

import org.mindrot.jbcrypt.BCrypt;

public class User {

    String username;
    String hashedPassword;
    int hold_id;

    public User() {
    }

    public User(String username, String password, int hold_id) {
        this.username = username;
        this.hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        this.hold_id = hold_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public int getHold_id() {
        return hold_id;
    }

    public void setHold_id(int hold_id) {
        this.hold_id = hold_id;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", hashedpassword='" + hashedPassword + '\'' +
                ", hold_id=" + hold_id +
                '}';
    }
}
