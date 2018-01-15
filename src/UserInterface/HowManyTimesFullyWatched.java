package UserInterface;

import Utillities.DatabaseHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

class HowManyTimesFullyWatched {
    private JPanel content;
    private GridBagConstraints constraints = new GridBagConstraints();
    private JComboBox selectAccount;
    private HashMap selectAccountList;
    private JLabel timesWatched;

    //Constructor
    HowManyTimesFullyWatched(){
        //Regelt content en layout
        this.content = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        this.content.setLayout(layout);

        //Maakt een JComboBox met de waarden uit de database
        selectAccountList = DatabaseHandler.getMovies();
        selectAccount = new JComboBox(selectAccountList.keySet().toArray());
        constraints.gridx = 0;
        constraints.gridy = 0;
        this.content.add(selectAccount, constraints);

        //Maakt een leeg JLabel aan zonder waarde
        timesWatched = new JLabel("Aantal keer volledig bekeken: -");
        constraints.gridx = 0;
        constraints.gridy = 1;
        this.content.add(timesWatched, constraints);

        //Actionlistener
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                ItemSelectable is = (ItemSelectable)actionEvent.getSource();
                selectedString(is);
            }
        };

        //Voegt de actionlistener toe aan de JComboBox
        selectAccount.addActionListener(actionListener);
    }

    //Methode om de actionlistener af te handelen
    private void selectedString(ItemSelectable is) {
        //Krijgt de waarde van momenteel geselecteerd item uit JComboBOx
        Object selected[] = is.getSelectedObjects();
        String selectedValue = selected[0].toString();

        //Stelt de waarde in van de JLabel met een waarde uit de database
        this.timesWatched.setText("Aantal keer volledig bekeken: " + DatabaseHandler.getMoviesFullyWatched(selectedValue));

        //Herlaadt de pagina content
        this.content.repaint();
        this.content.revalidate();
    }

    //Getter voor deze pagina
    JPanel getHowManyTimesFullyWatched(){
        return this.content;
    }
}

