package UserInterface.Edit;

import javax.swing.*;
import java.awt.*;

class EditProfiles {
    private JPanel content;

    EditProfiles(){
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

        JLabel currentName = new JLabel("Huidige naam: ");
        constraints.gridx = 0;
        constraints.gridy = 1;
        this.content.add(currentName, constraints);

        JTextArea nameValue = new JTextArea("--naam--");
        constraints.gridx = 1;
        constraints.gridy = 1;
        this.content.add(nameValue, constraints);

        JLabel currentDateOfBirth = new JLabel("Huidige geboortedatum: ");
        constraints.gridx = 0;
        constraints.gridy = 2;
        this.content.add(currentDateOfBirth, constraints);

        JTextArea dateOfBirthValue = new JTextArea("--geboortedatum--");
        constraints.gridx = 1;
        constraints.gridy = 2;
        this.content.add(dateOfBirthValue, constraints);

        JButton submit = new JButton("Opslaan");
        constraints.gridx = 0;
        constraints.gridy = 3;
        this.content.add(submit, constraints);

    }

    JPanel getEditProfiles(){
        return this.content;
    }
}
