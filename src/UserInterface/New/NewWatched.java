package UserInterface.New;

import javax.swing.*;
import java.awt.*;

class NewWatched {
    private JPanel content;

    NewWatched(){
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

    JLabel name = new JLabel("Percentage bekeken: ");
    constraints.gridx = 0;
    constraints.gridy = 1;
    this.content.add(name, constraints);

    JTextArea nameValue = new JTextArea("            ");
    constraints.gridx = 1;
    constraints.gridy = 1;
    this.content.add(nameValue, constraints);

    JButton submit = new JButton("Opslaan");
    constraints.gridx = 0;
    constraints.gridy = 2;
    this.content.add(submit, constraints);
    }

    JPanel getNewWatched(){
        return this.content;
    }
}
