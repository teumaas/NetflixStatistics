package UserInterface.Delete;

import javax.swing.*;
import java.awt.*;

class DeleteAccounts {
    private JPanel content;

    DeleteAccounts(){
        this.content = new JPanel();
        GridBagLayout layout = new GridBagLayout();

        GridBagConstraints constraints = new GridBagConstraints();
        this.content.setLayout(layout);

        String[] selectAccountList = {"-Selecteer account-"};
        JComboBox selectAccount = new JComboBox(selectAccountList);
        constraints.gridx = 0;
        constraints.gridy = 0;
        this.content.add(selectAccount, constraints);

        JButton submit = new JButton("Verwijder");
        constraints.gridx = 0;
        constraints.gridy = 1;
        this.content.add(submit, constraints);

    }

    JPanel getDeleteAccounts(){
        return this.content;
    }
}
