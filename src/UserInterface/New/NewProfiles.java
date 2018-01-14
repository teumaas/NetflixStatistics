package UserInterface.New;

import Utillities.DatabaseHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map;

class NewProfiles {
    private JPanel content;
    private Map<Integer, String> selectAccountList;
    private JComboBox selectAccount;
    private JComboBox dateOfBirthDayValue;
    private JTextArea nameValue;
    private ArrayList<String> profileInfo;
    private int AccountID;
    private String ProfileID;

    NewProfiles(){
        this.content = new JPanel();
        GridBagLayout layout = new GridBagLayout();

        GridBagConstraints constraints = new GridBagConstraints();
        this.content.setLayout(layout);

        profileInfo = new ArrayList<String>();
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

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                profileInfo.add(getCurrentAccountID());
                profileInfo.add(nameValue.getText());
                profileInfo.add(dateOfBirthValue.getText());

                try {
                    DatabaseHandler.setProfileInfo(profileInfo);
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }

                profileInfo.clear();
                nameValue.setText("            ");
                dateOfBirthValue.setText("            ");
            }
        });
    }

    public String getCurrentAccountID() {
        String AccountID = null;
        for (Map.Entry<Integer, String> entry : selectAccountList.entrySet()) {
            if (entry.getValue().equals(selectAccount.getSelectedItem())) {
                AccountID = entry.getKey().toString();
            }
        }
        return AccountID;
    }

    JPanel getNewProfiles(){
        return this.content;
    }
}
