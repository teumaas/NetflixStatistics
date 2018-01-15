package UserInterface;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserInterfaceTest {
    @Test
    void createLandingPage() {
        //Maakt een nieuw UserInterface object aan
        UserInterface test = new UserInterface();

        //Controleerd of de returnwaarde van de methode createLandingPage niet null is
        assertTrue(test.createLandingPage() != null);
    }

}