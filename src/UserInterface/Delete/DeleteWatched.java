package UserInterface.Delete;

import javax.swing.*;
import java.awt.*;

class DeleteWatched {
    private JPanel content;

    DeleteWatched() {
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

        String[] selectProgrammeList = {"-Selecteer programma-"};
        JComboBox selectProgramme = new JComboBox(selectProgrammeList);
        constraints.gridx = 0;
        constraints.gridy = 2;
        this.content.add(selectProgramme, constraints);

        JButton submit = new JButton("Verwijder");
        constraints.gridx = 0;
        constraints.gridy = 3;
        this.content.add(submit, constraints);
    }

    JPanel getDeleteWatched() {
        return this.content;
    }
}
