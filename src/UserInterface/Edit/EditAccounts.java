package UserInterface.Edit;

import UserInterface.UserInterface;
import Utillities.DatabaseHandler;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class EditAccounts {
    private JPanel content;

    public EditAccounts(){

        ArrayList<String> accounts = DatabaseHandler.getAccounts();

        this.content = new JPanel();
        GridBagLayout layout = new GridBagLayout();

        GridBagConstraints constraints = new GridBagConstraints();
        this.content.setLayout(layout);

        String[] selectAccountList = {accounts.get(1), accounts.get(7)};
        JComboBox selectAccount = new JComboBox(selectAccountList);
        constraints.gridx = 0;
        constraints.gridy = 0;
        this.content.add(selectAccount, constraints);

        JLabel currentName = new JLabel("Huidige naam: ");
        constraints.gridx = 0;
        constraints.gridy = 1;
        this.content.add(currentName, constraints);

        JTextArea nameValue = new JTextArea(accounts.get(1));
        constraints.gridx = 1;
        constraints.gridy = 1;
        this.content.add(nameValue, constraints);

        JLabel currentAdress = new JLabel("Huidig adres: ");
        constraints.gridx = 0;
        constraints.gridy = 2;
        this.content.add(currentAdress, constraints);

        JTextArea adressValue = new JTextArea(accounts.get(2));
        constraints.gridx = 1;
        constraints.gridy = 2;
        this.content.add(adressValue, constraints);

        JLabel currentHouseNumber = new JLabel("Huidig huisnummer: ");
        constraints.gridx = 0;
        constraints.gridy = 3;
        this.content.add(currentHouseNumber, constraints);

        JTextArea houseNumberValue = new JTextArea(accounts.get(3));
        constraints.gridx = 1;
        constraints.gridy = 3;
        this.content.add(houseNumberValue, constraints);

        JLabel currentPostalCode = new JLabel("Huidige postcode: ");
        constraints.gridx = 0;
        constraints.gridy = 4;
        this.content.add(currentPostalCode , constraints);

        JTextArea postalCodeValue = new JTextArea(accounts.get(4));
        constraints.gridx = 1;
        constraints.gridy = 4;
        this.content.add(postalCodeValue, constraints);

        JLabel currentPlace = new JLabel("Huidige woonplaats: ");
        constraints.gridx = 0;
        constraints.gridy = 5;
        this.content.add(currentPlace, constraints);

        JTextArea placeValue = new JTextArea(accounts.get(5));
        constraints.gridx = 1;
        constraints.gridy = 5;
        this.content.add(placeValue, constraints);

        JButton submit = new JButton("Opslaan");
        constraints.gridx = 0;
        constraints.gridy = 6;
        this.content.add(submit, constraints);
    }

    JPanel getEditAccounts(){
        return this.content;
    }
}
