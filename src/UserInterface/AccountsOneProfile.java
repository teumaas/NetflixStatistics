package UserInterface;

import Utillities.DatabaseHandler;

import javax.swing.*;
import java.awt.*;

class AccountsOneProfile {
    private JPanel content;

    AccountsOneProfile(){
        this.content = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        this.content.setLayout(layout);

        JLabel oneProfile = new JLabel("Aantal accounts met slechts één profiel: " + DatabaseHandler.accountsWithOneProfile());
        constraints.gridx = 0;
        constraints.gridy = 0;
        this.content.add(oneProfile, constraints);
    }

    JPanel getAccountsOneProfile(){
        return this.content;
    }
}
