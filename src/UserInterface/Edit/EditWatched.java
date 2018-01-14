package UserInterface.Edit;

import Utillities.DatabaseHandler;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

class EditWatched {
    private JPanel content;
    private Map<Integer, String> selectAccountList;
    private Map<Integer, String> selectProfileList;
    private Map<Integer, String> selectProgrammeList;
    private JComboBox selectAccount;
    private JComboBox selectProfile;
    private JComboBox selectProgramme;
    private JTextArea nameValue;
    private String profileID;

    EditWatched(){
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
        constraints.gridx = 1;
        constraints.gridy = 0;
        this.content.add(selectProfile, constraints);

        selectProgrammeList = new HashMap<Integer, String> ();
        selectProgrammeList.put(0, "--Selecteer programma--");
        selectProgramme = new JComboBox(loadPrograms().values().toArray());
        constraints.gridx = 2;
        constraints.gridy = 0;
        this.content.add(selectProgramme, constraints);

        JLabel currentName = new JLabel("Percentage bekeken: ");
        constraints.gridx = 0;
        constraints.gridy = 1;
        this.content.add(currentName, constraints);

        nameValue = new JTextArea("--percentage--");
        constraints.gridx = 1;
        constraints.gridy = 1;
        this.content.add(nameValue, constraints);

        JButton submit = new JButton("Opslaan");
        constraints.gridx = 0;
        constraints.gridy = 2;
        this.content.add(submit, constraints);

        loadProgrammeParentage();
    }

    private void loadProgrammeParentage() {
        for (Map.Entry<Integer, String> entry : selectProfileList.entrySet()) {
            if (entry.getValue().equals(selectProfile.getSelectedItem())) {
                profileID = entry.getKey().toString();

                nameValue.setText(null);
                nameValue.append(DatabaseHandler.getProgrammeParentage(entry.getKey()).get(0).toString());
            }
        }
    }

    public Map loadAccounts() {
        selectAccountList = DatabaseHandler.getAccountName();

        return selectAccountList;
    }

    private Map loadProfiles() {
        for (Map.Entry<Integer, String> entry : selectAccountList.entrySet()) {
            if (entry.getValue().equals(selectAccount.getSelectedItem())) {
                int AccountID = entry.getKey();
                selectProfileList = DatabaseHandler.getProfileName(AccountID);
            }
        }

        return selectProfileList;
    }

    private Map loadPrograms() {
        for (Map.Entry<Integer, String> entry : selectProfileList.entrySet()) {
            if (entry.getValue().equals(selectProfile.getSelectedItem())) {
                int ProfileID = entry.getKey();
                selectProgrammeList = DatabaseHandler.getProgrammeName(ProfileID);
            }
        }

        return selectProgrammeList;
    }

    JPanel getEditWatched(){
        return this.content;
    }
}
