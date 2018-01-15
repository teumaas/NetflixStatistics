package UserInterface.Edit;

import Utillities.DatabaseHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class EditAccounts {
    private JPanel content;
    private Map<Integer, String> selectAccountList;
    private JComboBox selectAccount;
    private JTextArea nameValue;
    private JTextArea addressValue;
    private JTextArea houseNumberValue;
    private JTextArea postalCodeValue;
    private JTextArea placeValue;
    private ArrayList<String> accountInfo;
    private String abonneeID;

    //Constructor
    EditAccounts(){
        //Maakt de content en layout aan.
        this.content = new JPanel();
        GridBagLayout layout = new GridBagLayout();

        GridBagConstraints constraints = new GridBagConstraints();
        this.content.setLayout(layout);

        //Maakt de content aan en voegt deze toe en laad gegevens uit de database.
        selectAccountList = new HashMap<Integer,String> ();
        selectAccountList.put(0, "--Selecteer account--");
        selectAccount = new JComboBox(loadAccounts().values().toArray());
        accountInfo = new ArrayList<String>();

        constraints.gridx = 0;
        constraints.gridy = 0;
        this.content.add(selectAccount, constraints);

        JLabel currentName = new JLabel("Huidige naam: ");
        constraints.gridx = 0;
        constraints.gridy = 1;
        content.add(currentName, constraints);

        JLabel currentAdress = new JLabel("Huidig adres: ");
        constraints.gridx = 0;
        constraints.gridy = 2;
        content.add(currentAdress, constraints);

        JLabel currentHouseNumber = new JLabel("Huidig huisnummer: ");
        constraints.gridx = 0;
        constraints.gridy = 3;
        content.add(currentHouseNumber, constraints);

        JLabel currentPostalCode = new JLabel("Huidige postcode: ");
        constraints.gridx = 0;
        constraints.gridy = 4;
        content.add(currentPostalCode, constraints);

        JLabel currentPlace = new JLabel("Huidige woonplaats: ");
        constraints.gridx = 0;
        constraints.gridy = 5;
        content.add(currentPlace, constraints);

        nameValue = new JTextArea();
        constraints.gridx = 1;
        constraints.gridy = 1;
        content.add(nameValue, constraints);

        addressValue = new JTextArea();
        constraints.gridx = 1;
        constraints.gridy = 2;
        content.add(addressValue, constraints);

        houseNumberValue = new JTextArea();
        constraints.gridx = 1;
        constraints.gridy = 3;
        content.add(houseNumberValue, constraints);

        postalCodeValue = new JTextArea();
        constraints.gridx = 1;
        constraints.gridy = 4;
        content.add(postalCodeValue, constraints);

        placeValue = new JTextArea();
        constraints.gridx = 1;
        constraints.gridy = 5;
        content.add(placeValue, constraints);

        JButton submit = new JButton("Opslaan");
        constraints.gridx = 0;
        constraints.gridy = 6;
        content.add(submit, constraints);

        loadAccountInfo();

        //Triggerd de Abonneegegevens lader.
        selectAccount.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadAccountInfo();
            }
        });

        // Stuurt de ingevulde gegevens door na de database.
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nameValue.getText().matches("[a-zA-Z- .]{1,64}") && addressValue.getText().matches("[a-zA-Z- ]{1,64}") && houseNumberValue.getText().matches("[0-9A-Z]{1,8}") &&  postalCodeValue.getText().matches("[0-9]{4}( ){1}[A-Z]{2}") && placeValue.getText().matches("[a-zA-Z- ]{1,64}"))
                {
                    accountInfo.add(abonneeID);
                    accountInfo.add(nameValue.getText());
                    accountInfo.add(addressValue.getText());
                    accountInfo.add(houseNumberValue.getText());
                    accountInfo.add(postalCodeValue.getText());
                    accountInfo.add(placeValue.getText());

                    DatabaseHandler.updateAccountInfo(accountInfo);

                    accountInfo.clear();
                    loadAccountInfo();
                    loadJComboBox();

                    JOptionPane.showMessageDialog(content,
                            "De gegevens zijn toegevoegd aan de database.",
                            "Succes!",
                            JOptionPane.QUESTION_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(content,
                            "Controleer de ingevoerde gegevens...",
                            "Validatiefout!",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        });
    }

    //Laad abonnee gegevens in de textvelden.
    private void loadAccountInfo() {
        for (Map.Entry<Integer, String> entry : selectAccountList.entrySet()) {
            if (entry.getValue().equals(selectAccount.getSelectedItem())) {
                abonneeID = entry.getKey().toString();

                nameValue.setText(null);
                nameValue.append(DatabaseHandler.getAccountInformation(entry.getKey()).get(0).toString());
                addressValue.setText(null);
                addressValue.append(DatabaseHandler.getAccountInformation(entry.getKey()).get(1).toString());
                houseNumberValue.setText(null);
                houseNumberValue.append(DatabaseHandler.getAccountInformation(entry.getKey()).get(2).toString());
                postalCodeValue.setText(null);
                postalCodeValue.append(DatabaseHandler.getAccountInformation(entry.getKey()).get(3).toString());
                placeValue.setText(null);
                placeValue.append(DatabaseHandler.getAccountInformation(entry.getKey()).get(4).toString());
            }
        }
    }

    // Returned abonneegegevens vanuit de database.
    public Map loadAccounts() {
        selectAccountList = DatabaseHandler.getAccountName();

        return selectAccountList;
    }

    // Laad abonneegegevens in JComboBox.
    public void loadJComboBox(){
        Iterator list = loadAccounts().values().iterator();
        int currentIndex = selectAccount.getSelectedIndex();
        selectAccount.removeAllItems();

        while (list.hasNext()) {
            selectAccount.addItem(list.next());
        }

        content.revalidate();
        content.repaint();
        selectAccount.setSelectedIndex(currentIndex);
    }

    JPanel getEditAccounts(){
        return this.content;
    }
}
