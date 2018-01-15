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
    private GridBagConstraints constraints = new GridBagConstraints();
    private JPanel moviesWatched;

    //Constructor
    MoviesWatchedAccount(){
        //Regelt de content layout
        this.content = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        this.content.setLayout(layout);

        //Maakt de JPanel voor de films aan
        this.moviesWatched = new JPanel();
        this.moviesWatched.setLayout(layout);

        //Maakt en voegt JComboBox toe
        selectAccountList = DatabaseHandler.getAccountName();
        selectAccount = new JComboBox(selectAccountList.values().toArray());
        constraints.gridx = 0;
        constraints.gridy = 0;
        this.content.add(selectAccount, constraints);

        //Voegt de JPanel voor de films toe
        constraints.gridx = 0;
        constraints.gridy = 1;
        this.content.add(moviesWatched, constraints);

        //Actionlistener
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                ItemSelectable is = (ItemSelectable)actionEvent.getSource();
                selectedString(is);
            }
        };

        //Voegt de actionListener toe
        selectAccount.addActionListener(actionListener);
    }

    //Handelt de actionListener af
    private void selectedString(ItemSelectable is) {
        //Pakt de momenteel geselecteerde waarde uit de JComboBox
        Object selected[] = is.getSelectedObjects();
        String selectedValue = selected[0].toString();

        //Stelt de constraints in voor de toe te voegen items
        GridBagConstraints constraints = new GridBagConstraints();

        //Cirkelt door de ArrayList met alle bekeken films en voegt ze stuk voor stuk toe
        int i = 0;
        this.moviesWatched.removeAll();
        ArrayList<String> moviesWatched = DatabaseHandler.getMoviesWatchedPerAccount(selectedValue);
        while (i < moviesWatched.size()) {
            JLabel movie = new JLabel(moviesWatched.get(i));
            constraints.gridx = 0;
            constraints.gridy = 0 + i;
            this.moviesWatched.add(movie, constraints);
            i++;
        }

        //Herlaadt de pagina
        this.content.repaint();
        this.content.revalidate();
    }

    //Getter voor de content
    JPanel getMoviesWatchedAccount(){
        return this.content;
    }
}
