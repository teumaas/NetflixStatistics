package UserInterface.Delete;

import Utillities.DatabaseHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class DeleteWatched {
    private JPanel content;
    private Map<Integer, String> selectAccountList;
    private Map<Integer, String> selectProfileList;
    private Map<Integer, String> selectProgrammeList;
    private JComboBox selectAccount;
    private JComboBox selectProfile;
    private JComboBox selectProgramme;

    DeleteWatched() {
        this.content = new JPanel();
        GridBagLayout layout = new GridBagLayout();

        GridBagConstraints constraints = new GridBagConstraints();
        this.content.setLayout(layout);

        selectAccountList = new HashMap<Integer,String> ();
        selectAccountList.put(0, "--Selecteer account--");
        selectAccount = new JComboBox(loadAccounts().values().toArray());

        constraints.gridx = 0;
        constraints.gridy = 0;
        this.content.add(selectAccount, constraints);

        selectProfileList = new HashMap<Integer,String> ();
        selectProfileList.put(0, "--Selecteer profiel--");
        selectProfile = new JComboBox(loadProfiles().values().toArray());

        constraints.gridx = 0;
        constraints.gridy = 1;
        this.content.add(selectProfile, constraints);

        selectProgrammeList = new HashMap<Integer, String> ();
        selectProgrammeList.put(0, "--Selecteer programma--");
        selectProgramme = new JComboBox(loadPrograms().values().toArray());
        constraints.gridx = 0;
        constraints.gridy = 2;
        this.content.add(selectProgramme, constraints);

        JButton submit = new JButton("Verwijder");
        constraints.gridx = 0;
        constraints.gridy = 3;
        this.content.add(submit, constraints);

        selectAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadJComboBox();
            }
        });

        selectProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadProfileJComboBox();
            }
        });

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Map.Entry<Integer, String> entry : selectProgrammeList.entrySet()) {
                    if (entry.getValue().equals(selectProgramme.getSelectedItem())) {
                        int ProgramID = entry.getKey();
                        DatabaseHandler.delete("ProfielProgramma", "ProfielID", GetProfileID(), "ProgrammaID", ProgramID);

                        JOptionPane.showMessageDialog(content,
                                "De gegevens zijn verwijderd uit de database.",
                                "Succes!",
                                JOptionPane.QUESTION_MESSAGE);

                        loadJComboBox();
                    }
                }
            }
        });
    }

    public Map loadAccounts() {
        selectAccountList = DatabaseHandler.getAccountName();

        return selectAccountList;
    }

    public int GetProfileID() {
        int ProfileID = 0;

        for (Map.Entry<Integer, String> entry : selectProfileList.entrySet()) {
            if (entry.getValue().equals(selectProfile.getSelectedItem())) {
             ProfileID = entry.getKey();
            }
        }

        return ProfileID;
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
                String ProfileID = entry.getKey().toString();
                selectProgrammeList = DatabaseHandler.getProgrammeNameIfPercentageExists(ProfileID);
            }
        }

        return selectProgrammeList;
    }

    private void loadJComboBox(){
        Iterator list = loadProfiles().values().iterator();
        selectProfile.removeAllItems();

        while (list.hasNext()) {
            selectProfile.addItem(list.next());
        }

        content.revalidate();
        content.repaint();
    }

    private void loadProfileJComboBox(){
        Iterator profiles = loadPrograms().values().iterator();
        selectProgramme.removeAllItems();

        while (profiles.hasNext()) {
            selectProgramme.addItem(profiles.next());
        }

        content.revalidate();
        content.repaint();
    }


    JPanel getDeleteWatched() {
        return this.content;
    }
}
