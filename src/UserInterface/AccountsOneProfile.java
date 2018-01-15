package UserInterface;

import Utillities.DatabaseHandler;

import javax.swing.*;
import java.awt.*;

class AccountsOneProfile {
    private JPanel content;
    private GridBagConstraints constraints = new GridBagConstraints();

    //Constructor
    AccountsOneProfile(){
        //Maakt de content en layout aan
        this.content = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        this.content.setLayout(layout);

        //Maakt de content aan en voegt deze toe
        JLabel oneProfile = new JLabel("Aantal accounts met slechts één profiel: " + DatabaseHandler.accountsWithOneProfile());
        constraints.gridx = 0;
        constraints.gridy = 0;
        this.content.add(oneProfile, constraints);
    }

    //Getter voor deze pagina
    JPanel getAccountsOneProfile(){
        return this.content;
    }
}
