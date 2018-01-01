package UserInterface;

import javax.swing.*;
import java.awt.*;

class MoviesWatchedAccount {
    private JPanel content;

    MoviesWatchedAccount(){
        this.content = new JPanel();

        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        this.content.setLayout(layout);

        String[] accountSelect = {"-Selecteer account-"};
        JComboBox selectList = new JComboBox(accountSelect);
        constraints.gridx = 0;
        constraints.gridy = 0;
        this.content.add(selectList, constraints);

        addMovie();

    }

    private void addMovie(/*List*/){
        GridBagConstraints constraints = new GridBagConstraints();

        JLabel addedMovie = new JLabel("Film");
        constraints.gridx = 0;
        constraints.gridy = 1;
        this.content.add(addedMovie, constraints);
    }

    JPanel getMoviesWatchedAccount(){
        return this.content;
    }
}
