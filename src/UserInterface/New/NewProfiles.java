package UserInterface.New;

import Utillities.DatabaseHandler;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

class NewProfiles {
    private JPanel content;
    private Map<Integer, String> selectAccountList;
    private JComboBox selectAccount;

    NewProfiles(){
        this.content = new JPanel();
        GridBagLayout layout = new GridBagLayout();

        GridBagConstraints constraints = new GridBagConstraints();
        this.content.setLayout(layout);

        selectAccountList = DatabaseHandler.getAccountName();
        selectAccount = new JComboBox(selectAccountList.values().toArray());
        constraints.gridx = 0;
        constraints.gridy = 0;
        this.content.add(selectAccount, constraints);

        JLabel name = new JLabel("Naam: ");
        constraints.gridx = 0;
        constraints.gridy = 1;
        this.content.add(name, constraints);

        JTextArea nameValue = new JTextArea("            ");
        constraints.gridx = 1;
        constraints.gridy = 1;
        this.content.add(nameValue, constraints);

        JLabel dateOfBirth = new JLabel("Geboortedatum: ");
        constraints.gridx = 0;
        constraints.gridy = 2;
        this.content.add(dateOfBirth, constraints);

        JTextArea dateOfBirthValue = new JTextArea("            ");
        constraints.gridx = 1;
        constraints.gridy = 2;
        this.content.add(dateOfBirthValue, constraints);

        JButton submit = new JButton("Opslaan");
        constraints.gridx = 0;
        constraints.gridy = 3;
        this.content.add(submit, constraints);
    }

    JPanel getNewProfiles(){
        return this.content;
    }
}
