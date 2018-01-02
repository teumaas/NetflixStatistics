package UserInterface;

import javax.swing.*;

class Footer {
    private JPanel footer;

    Footer(){
        this.footer = new JPanel();

        JLabel information = new JLabel("Informatica, 2017, Klas E, Tom Smits, Vincent Roeland, Sjoerd Schepers");
        JLabel title = new JLabel("Netflix Statistix");

        this.footer.add(title);
        this.footer.add(information);
    }

    JPanel getFooter(){
        return this.footer;
    }
}
