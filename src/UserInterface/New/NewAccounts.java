package UserInterface.New;

import javax.swing.*;
import java.awt.*;

public class NewAccounts {
    private JPanel content;

    public NewAccounts(){
        this.content = new JPanel();
        GridBagLayout layout = new GridBagLayout();

        GridBagConstraints constraints = new GridBagConstraints();
        this.content.setLayout(layout);
        JLabel name = new JLabel("Naam: ");
        constraints.gridx = 0;
        constraints.gridy = 0;
        this.content.add(name, constraints);

        JTextArea nameValue = new JTextArea("            ");
        constraints.gridx = 1;
        constraints.gridy = 0;
        this.content.add(nameValue, constraints);

        JLabel adress = new JLabel("Adres: ");
        constraints.gridx = 0;
        constraints.gridy = 1;
        this.content.add(adress, constraints);

        JTextArea adressValue = new JTextArea("            ");
        constraints.gridx = 1;
        constraints.gridy = 1;
        this.content.add(adressValue, constraints);

        JLabel houseNumber = new JLabel("Huisnummer: ");
        constraints.gridx = 0;
        constraints.gridy = 2;
        this.content.add(houseNumber, constraints);

        JTextArea houseNumberValue = new JTextArea("            ");
        constraints.gridx = 1;
        constraints.gridy = 2;
        this.content.add(houseNumberValue, constraints);

        JLabel postalCode = new JLabel("Postcode: ");
        constraints.gridx = 0;
        constraints.gridy = 3;
        this.content.add(postalCode , constraints);

        JTextArea postalCodeValue = new JTextArea("            ");
        constraints.gridx = 1;
        constraints.gridy = 3;
        this.content.add(postalCodeValue, constraints);

        JLabel place = new JLabel("Woonplaats: ");
        constraints.gridx = 0;
        constraints.gridy = 4;
        this.content.add(place, constraints);

        JTextArea placeValue = new JTextArea("            ");
        constraints.gridx = 1;
        constraints.gridy = 4;
        this.content.add(placeValue, constraints);

        JButton submit = new JButton("Opslaan");
        constraints.gridx = 0;
        constraints.gridy = 5;
        this.content.add(submit, constraints);
    }

    public JPanel getNewAccounts(){
        return this.content;
    }
}
