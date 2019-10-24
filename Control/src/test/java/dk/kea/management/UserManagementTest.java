package dk.kea.management;

import dk.kea.models.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserManagementTest {
    UserDbHandler userDbHandler = new UserDbHandler();

    @Test
    void testLogin(){
        assertEquals(true, userDbHandler.chkCredentials("taxi", "taxi"));
    }

    @Test
    void testGetAfdeling(){
        assertEquals("Taxi", userDbHandler.getAfdeling("taxi"));
    }

    @Test
    void testCreateDelete(){
        userDbHandler.create(new User("testUser", "password", 2, "email"));
        assertEquals(1, userDbHandler.delete("testUser"));
    }

}
