package UserInterface;

import javax.swing.*;

class Footer {
    private JPanel footer;

    //Constructor
    Footer(){
        //Maakt de footer en alle JLabels aan
        this.footer = new JPanel();
        JLabel information = new JLabel("Informatica, 2017, Klas E, Tom Smits, Vincent Roeland, Sjoerd Schepers");
        JLabel title = new JLabel("Netflix Statistix");

        //Voegt alle JLabels toe aan de footer
        this.footer.add(title);
        this.footer.add(information);
    }

    //Getter voor deze content
    JPanel getFooter(){
        return this.footer;
    }
}
