package dk.kea.management;

import dk.kea.models.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserManagementTest {


    @Test
    void testLogin(){

        UserDbHandler userDbHandler = new UserDbHandler();
        assertEquals(true, userDbHandler.chkCredentials("taxi", "taxi"));
    }

    @Test
    void testGetAfdeling(){
        UserDbHandler userDbHandler = new UserDbHandler();
        assertEquals("Taxi", userDbHandler.getAfdeling("taxi"));
    }

    @Test
    void deleteUser(){
        UserDbHandler userDbHandler = new UserDbHandler();
        userDbHandler.create(new User("testUser", "password", 2, "email"));
        assertEquals(1, userDbHandler.delete("testUser"));
    }

}
