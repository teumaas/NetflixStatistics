package UserInterface;

import javax.swing.*;

public class Footer {
    private JPanel footer;

    public Footer(){
        this.footer = new JPanel();

        this.footer.add(createTitle());
        this.footer.add(createInformation());
    }

    public JLabel createTitle(){
        JLabel title = new JLabel("Netflix Statistix");
        return title;
    }

    public JLabel createInformation(){
        JLabel information = new JLabel("Informatica, 2017, Klas E, Tom Smits, Vincent Roeland, Sjoerd Schepers");
        return information;
    }

    public JPanel getFooter(){
        return this.footer;
    }
}
