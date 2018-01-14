package UserInterface.Edit;

import Utillities.DatabaseHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class EditProfiles {
    private JPanel content;
    private Map<Integer, String> selectAccountList;
    private Map<Integer, String> selectProfileList;
    private JComboBox selectAccount;
    private JComboBox selectProfile;
    private JTextArea nameValue;
    private JTextArea dateOfBirthValue;
    private ArrayList<String> profileInfo;
    private int AccountID;
    private String ProfileID;

    EditProfiles(){
        this.content = new JPanel();
        GridBagLayout layout = new GridBagLayout();

        GridBagConstraints constraints = new GridBagConstraints();
        this.content.setLayout(layout);

        selectAccountList = new HashMap<Integer,String>();
        selectAccountList.put(0, "--Selecteer account--");
        selectAccount = new JComboBox(loadAccounts().values().toArray());
        constraints.gridx = 0;
        constraints.gridy = 0;
        this.content.add(selectAccount, constraints);

        selectProfileList = new HashMap<Integer,String> ();
        selectProfileList.put(0, "--Selecteer profiel--");
        selectProfile = new JComboBox(loadProfiles().values().toArray());
        profileInfo = new ArrayList<String>();

        constraints.gridx = 1;
        constraints.gridy = 0;
        this.content.add(selectProfile, constraints);

        JLabel currentName = new JLabel("Huidige naam: ");
        constraints.gridx = 0;
        constraints.gridy = 1;
        this.content.add(currentName, constraints);

        nameValue = new JTextArea("--naam--");
        constraints.gridx = 1;
        constraints.gridy = 1;
        this.content.add(nameValue, constraints);

        JLabel currentDateOfBirth = new JLabel("Huidige geboortedatum: ");
        constraints.gridx = 0;
        constraints.gridy = 2;
        this.content.add(currentDateOfBirth, constraints);

        dateOfBirthValue = new JTextArea("--geboortedatum--");
        constraints.gridx = 1;
        constraints.gridy = 2;
        this.content.add(dateOfBirthValue, constraints);

        JButton submit = new JButton("Opslaan");
        constraints.gridx = 0;
        constraints.gridy = 3;
        this.content.add(submit, constraints);

        loadProfileInfo();

        selectAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadJComboBox();
            }
        });


        selectProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadProfileInfo();
            }
        });


        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                profileInfo.add(ProfileID);
                profileInfo.add(nameValue.getText());
                profileInfo.add(dateOfBirthValue.getText());

                try {
                    DatabaseHandler.updateProfileInfo(profileInfo);
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }

                profileInfo.clear();
                loadProfileInfo();
                loadJComboBox();
            }
        });
    }

    public Map loadProfiles() {
        for (Map.Entry<Integer, String> entry : selectAccountList.entrySet()) {
            if (entry.getValue().equals(selectAccount.getSelectedItem())) {
                AccountID = entry.getKey();
                selectProfileList = DatabaseHandler.getProfileName(AccountID);
            }
        }

        return selectProfileList;
    }

    private void loadProfileInfo() {
        for (Map.Entry<Integer, String> entry : selectProfileList.entrySet()) {
            if (entry.getValue().equals(selectProfile.getSelectedItem())) {
                ProfileID = entry.getKey().toString();

                nameValue.setText(null);
                nameValue.append(DatabaseHandler.getProfileInformation(entry.getKey()).get(0).toString());
                dateOfBirthValue.setText(null);
                dateOfBirthValue.append(DatabaseHandler.getProfileInformation(entry.getKey()).get(1).toString());
            }
        }
    }

    public Map loadAccounts() {
        selectAccountList = DatabaseHandler.getAccountName();

        return selectAccountList;
    }

    public void loadJComboBox(){
        Iterator list = loadProfiles().values().iterator();
        selectProfile.removeAllItems();

        while (list.hasNext()) {
            selectProfile.addItem(list.next());
        }

        content.revalidate();
        content.repaint();
        selectProfile.setSelectedIndex(0);
    }

    JPanel getEditProfiles(){
        return this.content;
    }
}
