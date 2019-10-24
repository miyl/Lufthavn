package dk.kea.models;

public class User {

    String username;
    String plaintext_pw;
    int hold_id;
    String emailadress;
    User (){}

    public User(String username, String plaintext_pw, int hold_id, String emailadress) {
        this.username = username;
        this.plaintext_pw = plaintext_pw;
        this.hold_id = hold_id;
        this.emailadress = emailadress;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getPlaintext_pw() {
        return plaintext_pw;
    }

    public void setPlaintext_pw(String plaintext_pw) {
        this.plaintext_pw = plaintext_pw;
    }
}
