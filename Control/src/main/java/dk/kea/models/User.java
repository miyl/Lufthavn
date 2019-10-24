package dk.kea.models;

public class User {

    String username;
    String password;
    int hold_id;
    String emailadress;
    User (){}

    public User(String username, String password, int hold_id, String emailadress) {
        this.username = username;
        this.password = password;
        this.hold_id = hold_id;
        this.emailadress = emailadress;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getHold_id() {
        return hold_id;
    }

    public void setHold_id(int hold_id) {
        this.hold_id = hold_id;
    }

    public String getEmailadress() {
        return emailadress;
    }

    public void setEmailadress(String emailadress) {
        this.emailadress = emailadress;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", hold_id='" + hold_id + '\'' +
                ", emailadress='" + emailadress + '\'' +
                '}';
    }
}
