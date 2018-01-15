package UserInterface;

import Utillities.DatabaseHandler;

import javax.swing.*;
import java.awt.*;

class LongestMovieBelowSixteen {
    private JPanel content;

    //Constructor
    LongestMovieBelowSixteen(){
        //Regelt de content en layout
        this.content = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        this.content.setLayout(layout);

        //Stelt de tekst in met een waarde uit de database
        JLabel longestMovie = new JLabel("Langste film voor onder 16 jaar:  " + DatabaseHandler.longestMovieBelowSixteen());
        constraints.gridx = 0;
        constraints.gridy = 0;
        this.content.add(longestMovie, constraints);
    }

    //Getter voor de content
    JPanel getLongestMovieBelowSixteen(){
        return this.content;
    }
}
