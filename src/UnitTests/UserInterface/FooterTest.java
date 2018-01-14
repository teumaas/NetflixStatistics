package UserInterface;

import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class FooterTest {
    @Test
    void getFooter() {
        Footer test = new Footer();
        assertTrue(test.getFooter().getClass().getName() == "javax.swing.JPanel");
    }

}