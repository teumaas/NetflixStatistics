package UserInterface;

import javax.swing.*;
import java.awt.*;

class LandingPage {
    private JPanel content;

    //Constructor
    LandingPage(){
        //Regelt de layout en content
        this.content = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        this.content.setLayout(layout);

        //Maakt het eerste kopje aan, stelt hem in en voegt hem toe
        JLabel title = new JLabel("Welkom bij Netflix Statistix");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;
        this.content.add(title, constraints);

        //Maakt de ondertitel, stelt hem in en voegt hem toe
        JLabel subTitle = new JLabel("Een applicatie gemaakt in opdracht van Avans Hogeschool");
        subTitle.setFont(new Font("Arial", Font.BOLD, 14));
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.WEST;
        this.content.add(subTitle, constraints);
    }

    //Getter voor deze pagina
    JPanel getLandingPage(){
        return this.content;
    }
}
