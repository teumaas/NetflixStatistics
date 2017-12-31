package UserInterface;

import javax.swing.*;
import java.awt.*;

public class LongestMovieBelowSixteen {
    private JPanel content;

    public LongestMovieBelowSixteen(){
         this.content = new JPanel();

        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        this.content.setLayout(layout);

        JLabel longestMovie = new JLabel("Langste film voor onder 16 jaar:  ");
        constraints.gridx = 0;
        constraints.gridy = 0;
        this.content.add(longestMovie, constraints);
    }

    public JPanel getLongestMovieBelowSixteen(){
        return this.content;
    }
}
