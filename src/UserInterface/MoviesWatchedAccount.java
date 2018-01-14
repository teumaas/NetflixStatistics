package UserInterface;

import Utillities.DatabaseHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

class MoviesWatchedAccount {
    private JPanel content;
    private Map<Integer, String> selectAccountList;
    private JComboBox selectAccount;

    MoviesWatchedAccount(){
        this.content = new JPanel();

        GridBagLayout layout = new GridBagLayout();
        this.content.setLayout(layout);

        addSelector();
    }

    private void selectedString(ItemSelectable is) {
        Object selected[] = is.getSelectedObjects();
        String selectedValue = selected[0].toString();
        addMovie(selectedValue);
    }

    private void addSelector(){
        GridBagConstraints constraints = new GridBagConstraints();

        selectAccountList = DatabaseHandler.getAccountName();
        selectAccount = new JComboBox(selectAccountList.values().toArray());
        constraints.gridx = 0;
        constraints.gridy = 0;
        this.content.add(selectAccount, constraints);

        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                ItemSelectable is = (ItemSelectable)actionEvent.getSource();
                selectedString(is);
            }
        };
        selectAccount.addActionListener(actionListener);
    }

    private void addMovie(String value){
        this.content.removeAll();
        addSelector();

        GridBagConstraints constraints = new GridBagConstraints();
        int i = 0;

        ArrayList<String> moviesWatched = DatabaseHandler.getMoviesWatchedPerAccount(value);
        if (moviesWatched.isEmpty()){
            JLabel addedMovie = new JLabel("-");
            constraints.gridx = 0;
            constraints.gridy = 1;
            this.content.add(addedMovie, constraints);
        }

        else {
            while (i < moviesWatched.size()) {
                JLabel addedMovie = new JLabel(moviesWatched.get(i));
                constraints.gridx = 0;
                constraints.gridy = i + 1;
                this.content.add(addedMovie, constraints);
                i++;
            }
            this.content.repaint();
            this.content.revalidate();
        }

    }

    JPanel getMoviesWatchedAccount(){
        return this.content;
    }
}
