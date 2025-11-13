package se.bytebase.vr_server.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private ScoreModel user;
    private final LocalDateTime now = LocalDateTime.now();

    @BeforeEach
    void setUp() {
        user = new ScoreModel("Test", "User",
                "TestUser", "password123",
                "blonde", "test.user@test.com",
                "1234567890"
        );
        user.setId(1L);
        user.setSignupDate(now);
    }

    @Test
    void createUser() {
        assertNotNull(user);
        assertEquals("Test", user.getFirstName());
        assertEquals("User", user.getLastName());
        assertEquals("TestUser", user.getUsername());
        assertEquals("password123", user.getPassword());
        assertEquals("blonde", user.getCatColor());
        assertEquals("test.user@test.com", user.getEmail());
        assertEquals("1234567890", user.getPhone());
        assertEquals(now, user.getSignupDate());
    }

    @Test
    void updateUser() {
        user.setFirstName("Test2");
        user.setLastName("User2");

        assertEquals("Test2", user.getFirstName());
        assertEquals("User2", user.getLastName());
    }

    @Test
    void massUserCreation() {
        Set<String> usernames = new HashSet<>();
        Thread t = new Thread();
        for (int i = 0; i <= 9999; i++) {
            ScoreModel u = new ScoreModel("First" + i, "Last" + i,
                    "user" + i, "pass" + i, "color" + i,
                    "user" + i + "@test.com", "070" + i);
            u.setId((long) i);
            u.setSignupDate(now);

            // basic assertions
            assertNotNull(u);
            assertTrue(u.getUsername().startsWith("user"));
            assertEquals("user" + i + "@test.com", u.getEmail());

            // uniqueness check
            assertTrue(usernames.add(u.getUsername()), "Duplicate username detected: " + u.getUsername());
            System.out.println("Created id: " + u.getId() + ", username: " + u.getUsername());
        }
        assertEquals(10000, usernames.size());
    }

    @Test
    void testEqualityAndHashing() {
        ScoreModel user2 = new ScoreModel("Test", "User",
                "TestUser", "password123",
                "blonde", "test.user@test.com",
                "1234567890");
        user2.setId(1L);
        user2.setSignupDate(now);

        assertEquals(user, user2);
        assertEquals(user.hashCode(), user2.hashCode());
    }

    @Test
    void testInvalidEmail() {
        ScoreModel invalidUser = new ScoreModel("Test2", "User2", "TestUser2", "TestPass2",
                "black", "not-an=email", "not-an-phone-number");

        String email = invalidUser.getEmail();
        boolean emailLooksValid = email != null && email.contains("@") && email.contains(".");

        String phoneNumber = invalidUser.getPhone();
        //boolean phoneLooksValid = phoneNumber != null &! phoneNumber.contains(char);

        assertFalse(emailLooksValid, "Email format should be invalid here");
    }


    @Test
    void testNullFields() {
        ScoreModel nullUser = new ScoreModel(null, null, null, null, null, null, null);
        assertNull(nullUser.getFirstName());
        assertNull(nullUser.getUsername());
    }

    @Test
    void testPasswordChange() {
        user.setPassword("newPassword!");
        assertEquals("newPassword!", user.getPassword());
        assertNotEquals("password123", user.getPassword());
    }
}
