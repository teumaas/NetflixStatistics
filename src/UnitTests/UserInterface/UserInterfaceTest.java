package UserInterface;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserInterfaceTest {
    @Test
    void createLandingPage() {
        UserInterface test = new UserInterface();
        assertTrue(test.createLandingPage() != null);
    }

}