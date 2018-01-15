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

    //Constructor
    NewWatched(){

    //Maakt de content en layout aan.
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
    Item account = (Item)selectAccount.getSelectedItem();
    String aid = (String)account.getValue();
    loadProfiles(Integer.parseInt(aid)).forEach((key, value) -> selectProfile.addItem( new Item<String>(key.toString(), value.toString() ) ));

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

    //Triggerd de Profielgegevens JComboBox lader.
    selectAccount.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            loadProfilesJComboBox();
        }
    });

    //Triggerd de Programmagegevens lader.
    selectProfile.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            loadProgramsJComboBox();
        }
    });

    // Stuurt de ingevulde gegevens door na de database.
    submit.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Item profile = (Item)selectProgramme.getSelectedItem();
            String pid = (String)profile.getValue();

            Item item = (Item)selectProfile.getSelectedItem();
            String code = (String)item.getValue();

            if (nameValue.getText().matches("([0-9]{2})|([0-9]{1})|((100){1})")) {
                profileInfo.add(nameValue.getText());
                profileInfo.add(pid);
                profileInfo.add(code);

                DatabaseHandler.setPercentage(profileInfo);

                profileInfo.clear();

                nameValue.setText("            ");
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

    private void loadProfilesJComboBox(){
        Item item = (Item)selectAccount.getSelectedItem();
        String code = (String)item.getValue();

        selectProfile.removeAllItems();

        loadProfiles(Integer.parseInt(code)).forEach((key, value) -> selectProfile.addItem( new Item<String>(key.toString(), value.toString() ) ));

        content.revalidate();
        content.repaint();
    }

    private void loadProgramsJComboBox(){
        selectProgramme.removeAllItems();

        loadPrograms().forEach((key, value) -> selectProgramme.addItem( new Item<String>(key.toString(), value.toString() ) ));

        content.revalidate();
        content.repaint();
    }

    // Returned gegevens vanuit de database.
    private Map loadAccounts() {
        selectAccountList.clear();

        selectAccountList = DatabaseHandler.getAccountName();

        return selectAccountList;
    }

    // Returned gegevens vanuit de database.
    private Map loadProfiles(int id) {
        selectProfileList.clear();

        selectProfileList = DatabaseHandler.getProfileName(id);

        return selectProfileList;
    }

    // Returned gegevens vanuit de database.
    private Map loadPrograms() {
        selectProgrammeList.clear();

        selectProgrammeList = DatabaseHandler.getProgrammeName();

        return selectProgrammeList;
    }

    JPanel getNewWatched(){
        return this.content;
    }
}
