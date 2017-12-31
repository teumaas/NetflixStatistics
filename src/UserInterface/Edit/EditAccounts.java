package UserInterface.Edit;

import javax.swing.*;
import java.awt.*;

public class EditAccounts {
    private JPanel content;

    public EditAccounts(){
        this.content = new JPanel();
        GridBagLayout layout = new GridBagLayout();

        GridBagConstraints constraints = new GridBagConstraints();
        this.content.setLayout(layout);

        String[] selectAccountList = {"-Selecteer account-"};
        JComboBox selectAccount = new JComboBox(selectAccountList);
        constraints.gridx = 0;
        constraints.gridy = 0;
        this.content.add(selectAccount, constraints);

        JLabel currentName = new JLabel("Huidige naam: ");
        constraints.gridx = 0;
        constraints.gridy = 1;
        this.content.add(currentName, constraints);

        JTextArea nameValue = new JTextArea("--naam--");
        constraints.gridx = 1;
        constraints.gridy = 1;
        this.content.add(nameValue, constraints);

        JLabel currentAdress = new JLabel("Huidig adres: ");
        constraints.gridx = 0;
        constraints.gridy = 2;
        this.content.add(currentAdress, constraints);

        JTextArea adressValue = new JTextArea("--adres--");
        constraints.gridx = 1;
        constraints.gridy = 2;
        this.content.add(adressValue, constraints);

        JLabel currentHouseNumber = new JLabel("Huidig huisnummer: ");
        constraints.gridx = 0;
        constraints.gridy = 3;
        this.content.add(currentHouseNumber, constraints);

        JTextArea houseNumberValue = new JTextArea("--huisnummer--");
        constraints.gridx = 1;
        constraints.gridy = 3;
        this.content.add(houseNumberValue, constraints);

        JLabel currentPostalCode = new JLabel("Huidige postcode: ");
        constraints.gridx = 0;
        constraints.gridy = 4;
        this.content.add(currentPostalCode , constraints);

        JTextArea postalCodeValue = new JTextArea("--postcode--");
        constraints.gridx = 1;
        constraints.gridy = 4;
        this.content.add(postalCodeValue, constraints);

        JLabel currentPlace = new JLabel("Huidige woonplaats: ");
        constraints.gridx = 0;
        constraints.gridy = 5;
        this.content.add(currentPlace, constraints);

        JTextArea placeValue = new JTextArea("--woonplaats--");
        constraints.gridx = 1;
        constraints.gridy = 5;
        this.content.add(placeValue, constraints);

        JButton submit = new JButton("Opslaan");
        constraints.gridx = 0;
        constraints.gridy = 6;
        this.content.add(submit, constraints);

    }

    public JPanel getEditAccounts(){
        return this.content;
    }
}
