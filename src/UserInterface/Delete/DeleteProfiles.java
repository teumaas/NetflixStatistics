package UserInterface.Delete;

import javax.swing.*;
import java.awt.*;


public class DeleteProfiles {
    private JPanel content;

    public DeleteProfiles() {
        this.content = new JPanel();
        GridBagLayout layout = new GridBagLayout();

        GridBagConstraints constraints = new GridBagConstraints();
        this.content.setLayout(layout);

        String[] selectAccountList = {"-Selecteer account-"};
        JComboBox selectAccount = new JComboBox(selectAccountList);
        constraints.gridx = 0;
        constraints.gridy = 0;
        this.content.add(selectAccount, constraints);

        String[] selectProfileList = {"-Selecteer profiel-"};
        JComboBox selectProfile = new JComboBox(selectProfileList);
        constraints.gridx = 0;
        constraints.gridy = 1;
        this.content.add(selectProfile, constraints);

        JButton submit = new JButton("Verwijder");
        constraints.gridx = 0;
        constraints.gridy = 2;
        this.content.add(submit, constraints);
    }

    public JPanel getDeleteprofiles(){
        return this.content;
    }
}
