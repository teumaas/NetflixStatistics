package UserInterface.New;

import Utillities.DatabaseHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class NewAccounts {
    private JPanel content;
    private JTextArea idValue;
    private JTextArea nameValue;
    private JTextArea addressValue;
    private JTextArea houseNumberValue;
    private JTextArea postalCodeValue;
    private JTextArea placeValue;

    private ArrayList<String> accountInfo;
    private String abonneeID;

    NewAccounts(){
        this.content = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        accountInfo = new ArrayList<String>();

        GridBagConstraints constraints = new GridBagConstraints();
        this.content.setLayout(layout);

        JLabel id = new JLabel("Abonnee ID: ");
        constraints.gridx = 0;
        constraints.gridy = 0;
        this.content.add(id, constraints);

        idValue = new JTextArea("            ");
        constraints.gridx = 1;
        constraints.gridy = 0;
        this.content.add(idValue, constraints);

        JLabel name = new JLabel("Naam: ");
        constraints.gridx = 0;
        constraints.gridy = 1;
        this.content.add(name, constraints);

        nameValue = new JTextArea("            ");
        constraints.gridx = 1;
        constraints.gridy = 1;
        this.content.add(nameValue, constraints);

        JLabel address = new JLabel("Adres: ");
        constraints.gridx = 0;
        constraints.gridy = 2;
        this.content.add(address, constraints);

        addressValue = new JTextArea("            ");
        constraints.gridx = 1;
        constraints.gridy = 2;
        this.content.add(addressValue, constraints);

        JLabel houseNumber = new JLabel("Huisnummer: ");
        constraints.gridx = 0;
        constraints.gridy = 3;
        this.content.add(houseNumber, constraints);

        houseNumberValue = new JTextArea("            ");
        constraints.gridx = 1;
        constraints.gridy = 3;
        this.content.add(houseNumberValue, constraints);

        JLabel postalCode = new JLabel("Postcode: ");
        constraints.gridx = 0;
        constraints.gridy = 4;
        this.content.add(postalCode , constraints);

        postalCodeValue = new JTextArea("            ");
        constraints.gridx = 1;
        constraints.gridy = 4;
        this.content.add(postalCodeValue, constraints);

        JLabel place = new JLabel("Woonplaats: ");
        constraints.gridx = 0;
        constraints.gridy = 5;
        this.content.add(place, constraints);

        placeValue = new JTextArea("            ");
        constraints.gridx = 1;
        constraints.gridy = 5;
        this.content.add(placeValue, constraints);

        JButton submit = new JButton("Opslaan");
        constraints.gridx = 0;
        constraints.gridy = 6;
        this.content.add(submit, constraints);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accountInfo.add(idValue.getText());
                accountInfo.add(nameValue.getText());
                accountInfo.add(addressValue.getText());
                accountInfo.add(houseNumberValue.getText());
                accountInfo.add(postalCodeValue.getText());
                accountInfo.add(placeValue.getText());

                DatabaseHandler.setAccountInfo(accountInfo);
            }
        });

    }

    JPanel getNewAccounts(){
        return this.content;
    }
}
