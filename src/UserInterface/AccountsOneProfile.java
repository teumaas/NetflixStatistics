package UserInterface;

import javax.swing.*;
import java.awt.*;

public class AccountsOneProfile {
    private JPanel content;

    public AccountsOneProfile(){
        this.content = new JPanel();
        GridBagLayout layout = new GridBagLayout();

        GridBagConstraints constraints = new GridBagConstraints();
        this.content.setLayout(layout);

        JLabel oneProfile = new JLabel("Aantal accounts met slechts één profiel: ");
        constraints.gridx = 0;
        constraints.gridy = 0;
        this.content.add(oneProfile, constraints);
    }

    public JPanel getAccountsOneProfile(){
        return this.content;
    }
}
