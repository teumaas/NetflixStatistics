package UserInterface.Delete;

import Utillities.DatabaseHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;


class DeleteProfiles {
    private JPanel content;
    private Map<Integer, String> selectAccountList;
    private Map<Integer, String> selectProfileList;
    private JComboBox selectAccount;
    private JComboBox selectProfile;

    DeleteProfiles() {
        this.content = new JPanel();
        GridBagLayout layout = new GridBagLayout();

        GridBagConstraints constraints = new GridBagConstraints();
        this.content.setLayout(layout);

        selectAccountList = DatabaseHandler.getAccountName();
        selectAccount = new JComboBox(selectAccountList.values().toArray());

        constraints.gridx = 0;
        constraints.gridy = 0;
        this.content.add(selectAccount, constraints);

        selectProfileList = DatabaseHandler.
        selectProfile = new JComboBox(selectProfileList);
        constraints.gridx = 0;
        constraints.gridy = 1;
        this.content.add(selectProfile, constraints);

        JButton submit = new JButton("Verwijder");
        constraints.gridx = 0;
        constraints.gridy = 2;
        this.content.add(submit, constraints);

        selectProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accountInfo.add(abonneeID);
                accountInfo.add(nameValue.getText());
                accountInfo.add(addressValue.getText());
                accountInfo.add(houseNumberValue.getText());
                accountInfo.add(postalCodeValue.getText());
                accountInfo.add(placeValue.getText());

                DatabaseHandler.updateAccountInfo(accountInfo);
            }
        });
    }

    JPanel getDeleteprofiles(){
        return this.content;
    }
}
