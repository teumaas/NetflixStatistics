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

        selectAccountList = DatabaseHandler.getAccountName();
        selectAccount = new JComboBox(selectAccountList.values().toArray());

        constraints.gridx = 0;
        constraints.gridy = 0;
        this.content.add(selectAccount, constraints);

        selectProfileList = new HashMap<Integer,String> ();
        selectProfileList.put(0, "--Selecteer profiel--");
        selectProfile = new JComboBox(loadProfiles().values().toArray());

        constraints.gridx = 0;
        constraints.gridy = 1;
        this.content.add(selectProfile, constraints);

        selectProgrammeList = new HashMap<Integer,String> ();
        selectProgrammeList.put(0, "-Selecteer programma-");
        selectProgramme = new JComboBox(loadProgrammas().values().toArray());
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
    }

    public Map loadProfiles() {
        for (Map.Entry<Integer, String> entry : selectAccountList.entrySet()) {
            if (entry.getValue().equals(selectAccount.getSelectedItem())) {
                int AccountID = entry.getKey();
                selectProfileList = DatabaseHandler.getProfileName(AccountID);
            }
        }

        return selectProfileList;
    }

    public Map loadProgrammas() {
        int AccountID = 0;

        for (Map.Entry<Integer, String> entryTwo : selectProgrammeList.entrySet()) {
            if (entryTwo.getValue().equals(selectProfile.getSelectedItem())) {
                int ProfileID = entryTwo.getKey();

            }
        }

        return selectProgrammeList;
    }

    public void loadJComboBox(){
        Iterator list = loadProfiles().values().iterator();
        selectProfile.removeAllItems();

        while (list.hasNext()) {
            selectProfile.addItem(list.next());
        }

        content.revalidate();
        content.repaint();
    }

    public void loadProfileJComboBox(){
        Iterator list = loadProgrammas().values().iterator();
        selectProgramme.removeAllItems();

        while (list.hasNext()) {
            selectProgramme.addItem(list.next());
        }

        content.revalidate();
        content.repaint();
    }


    JPanel getDeleteWatched() {
        return this.content;
    }
}
