package UserInterface;

import javax.swing.*;
import java.awt.*;

public class EditWatched {
    private JPanel content;

    public EditWatched(){
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
        constraints.gridx = 1;
        constraints.gridy = 0;
        this.content.add(selectProfile, constraints);

        String[] selectProgrammeList = {"-Selecteer programma-"};
        JComboBox selectProgramme = new JComboBox(selectProgrammeList);
        constraints.gridx = 2;
        constraints.gridy = 0;
        this.content.add(selectProgramme, constraints);

        JLabel currentName = new JLabel("Percentage bekeken: ");
        constraints.gridx = 0;
        constraints.gridy = 1;
        this.content.add(currentName, constraints);

        JTextArea nameValue = new JTextArea("--percentage--");
        constraints.gridx = 1;
        constraints.gridy = 1;
        this.content.add(nameValue, constraints);

        JButton submit = new JButton("Opslaan");
        constraints.gridx = 0;
        constraints.gridy = 2;
        this.content.add(submit, constraints);
    }

    public JPanel getEditWatched(){
        return this.content;
    }
}
