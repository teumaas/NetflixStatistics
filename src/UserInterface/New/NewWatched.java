package UserInterface.New;

import Utillities.DatabaseHandler;
import Utillities.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class NewWatched {
    private JPanel content;

    private Map<Integer, String> selectAccountList;
    private Map<Integer, String> selectProfileList;
    private Map<Integer, String> selectProgrammeList;
    private JComboBox<Item<String>> selectAccount;;
    private JComboBox<Item<String>> selectProfile;
    private JComboBox<Item<String>> selectProgramme;
    private ArrayList<String> profileInfo;
    private String profileID;

    NewWatched(){
    this.content = new JPanel();
    GridBagLayout layout = new GridBagLayout();
    profileInfo = new ArrayList<String>();

    GridBagConstraints constraints = new GridBagConstraints();
    this.content.setLayout(layout);

    selectAccountList = new HashMap<Integer,String>();
    selectAccountList.put(0, "--Selecteer profiel--");
    selectAccount = new JComboBox<Item<String>>();
    loadAccounts().forEach((key, value) -> selectAccount.addItem( new Item<String>(key.toString(), value.toString() ) ));

    constraints.gridx = 0;
    constraints.gridy = 0;
    this.content.add(selectAccount, constraints);

    selectProfileList = new HashMap<Integer,String>();
    selectProfileList.put(0, "--Selecteer profiel--");
    selectProfile = new JComboBox<Item<String>>();
    loadProfiles().forEach((key, value) -> selectProfile.addItem( new Item<String>(key.toString(), value.toString() ) ));

    constraints.gridx = 1;
    constraints.gridy = 0;
    this.content.add(selectProfile, constraints);

    selectProgrammeList = new HashMap<Integer, String> ();
    selectProgrammeList.put(0, "--Selecteer programma--");
    selectProgramme = new JComboBox<Item<String>>();
    loadPrograms().forEach((key, value) -> selectProgramme.addItem( new Item<String>(key.toString(), value.toString() ) ));

    constraints.gridx = 2;
    constraints.gridy = 0;
    this.content.add(selectProgramme, constraints);

    JLabel name = new JLabel("Percentage bekeken: ");
    constraints.gridx = 0;
    constraints.gridy = 1;
    this.content.add(name, constraints);

    JTextArea nameValue = new JTextArea("            ");
    constraints.gridx = 1;
    constraints.gridy = 1;
    this.content.add(nameValue, constraints);

    JButton submit = new JButton("Opslaan");
    constraints.gridx = 0;
    constraints.gridy = 2;
    this.content.add(submit, constraints);

    selectAccount.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            loadProfilesJComboBox();
        }
    });

    selectProfile.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            loadProgramsJComboBox();
        }
    });

    submit.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            Item item = (Item)selectProfile.getSelectedItem();
            String code = (String)item.getValue();

            profileInfo.add(code);
            profileInfo.add(nameValue.getText());

            DatabaseHandler.updatePercentage(profileInfo);

            profileInfo.clear();

            nameValue.setText("            ");
        }
    });
    }

    private void loadProfilesJComboBox(){
        selectProfile.removeAllItems();

        loadProfiles().forEach((key, value) -> selectProfile.addItem( new Item<String>(key.toString(), value.toString() ) ));

        content.revalidate();
        content.repaint();
    }

    private void loadProgramsJComboBox(){
        selectProgramme.removeAllItems();

        loadPrograms().forEach((key, value) -> selectProgramme.addItem( new Item<String>(key.toString(), value.toString() ) ));

        content.revalidate();
        content.repaint();
    }

    private Map loadAccounts() {
        selectAccountList.clear();

        selectAccountList = DatabaseHandler.getAccountName();

        return selectAccountList;
    }

    private Map loadProfiles() {
        selectProfileList.clear();

        Item item = (Item)selectAccount.getSelectedItem();
        String code = (String)item.getValue();

        selectProfileList = DatabaseHandler.getProfileName(Integer.parseInt(code));

        return selectProfileList;
    }

    private Map loadPrograms() {
        selectProgrammeList.clear();

        Item item = (Item)selectProfile.getSelectedItem();
        String code = (String)item.getValue();

        selectProgrammeList = DatabaseHandler.getProgrammeName(Integer.parseInt(code));

        return selectProgrammeList;
    }

    JPanel getNewWatched(){
        return this.content;
    }
}
