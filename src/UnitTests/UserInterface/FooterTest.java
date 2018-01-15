package UserInterface;

import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class FooterTest {
    @Test
    void getFooter() {
        //Maakt footer object aan
        Footer test = new Footer();

        //Controleerd of de methode getFooter wel een JPanel als returnwaarde heeft
        assertTrue(test.getFooter().getClass().getName() == "javax.swing.JPanel");
    }

}