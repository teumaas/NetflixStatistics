package UserInterface.Edit;

import Utillities.DatabaseHandler;
import Utillities.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class EditWatched {
    private JPanel content;
    private JTextArea nameValue;
    private Map<Integer, String> selectAccountList;
    private Map<Integer, String> selectProfileList;
    private Map<Integer, String> selectProgrammeList;
    private JComboBox<Item<String>> selectAccount;;
    private JComboBox<Item<String>> selectProfile;
    private JComboBox<Item<String>> selectProgramme;
    private ArrayList<String> profileInfo;

    //Constructor
    EditWatched(){

        //Maakt de content en layout aan.
        this.content = new JPanel();
        GridBagLayout layout = new GridBagLayout();

        GridBagConstraints constraints = new GridBagConstraints();
        this.content.setLayout(layout);

        //Maakt de content aan en voegt deze toe en laad gegevens uit de database.
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

        loadPercentage();

        //Triggerd de Percentage lader en JComboBox lader voor abonnees.
        selectAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadPercentage();
                loadProfilesJComboBox();
            }
        });

        //Triggerd de Percentage lader en JComboBox lader voor abonnees.
        selectProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadPercentage();
                loadProgramsJComboBox();
            }
        });

        //Triggerd de Percentage lader
        selectProgramme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadPercentage();
            }
        });

        // Stuurt de ingevulde gegevens door na de database.
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nameValue.getText().matches("([0-9]{2})|([0-9]{1})|((100){1})")) {
                    profileInfo = new ArrayList<String>();

                    Item profile = (Item)selectProgramme.getSelectedItem();
                    String pid = (String)profile.getValue();

                    Item item = (Item)selectProfile.getSelectedItem();
                    String code = (String)item.getValue();

                    profileInfo.add(nameValue.getText().toString());
                    profileInfo.add(code);
                    profileInfo.add(pid);

                    DatabaseHandler.updatePercentage(profileInfo);

                    profileInfo.clear();

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

    //Laad percentage vanuit de database.
    private void loadPercentage() {
        Item proID = (Item)selectProgramme.getSelectedItem();
        String proIDOut = (String)proID.getValue();
        Item pID = (Item)selectProfile.getSelectedItem();
        String pIDOut = (String)pID.getValue();

        nameValue.setText(DatabaseHandler.getProgrammeParentage(pIDOut, proIDOut).values().toString().replace("[", "").replace("]", ""));
    }

    // Laad JComboBox met abonneegegevens.
    private void loadProfilesJComboBox(){
        Item item = (Item)selectAccount.getSelectedItem();
        String code = (String)item.getValue();

        loadProfiles(Integer.parseInt(code)).forEach((key, value) -> selectProfile.addItem( new Item<String>(key.toString(), value.toString() ) ));

        content.revalidate();
        content.repaint();
    }

    // Laad JComboBox met programmasgegevens.
    private void loadProgramsJComboBox(){
        loadPrograms().forEach((key, value) -> selectProgramme.addItem( new Item<String>(key.toString(), value.toString() )));

        content.revalidate();
        content.repaint();
    }

    // Laad de abonneegegevens vanuit de database.
    private Map loadAccounts() {
        selectAccountList.clear();

        selectAccountList = DatabaseHandler.getAccountName();

        return selectAccountList;
    }

    // Laad de profielgegevens vanuit de database.
    private Map loadProfiles(int id) {
        selectProfileList.clear();

        selectProfileList = DatabaseHandler.getProfileName(id);
        return selectProfileList;
    }

    // Laad de programma vanuit de database.
    private Map loadPrograms() {
        selectProgrammeList.clear();

        Item pID = (Item)selectProfile.getSelectedItem();
        String pIDOut = (String)pID.getValue();

        selectProgrammeList = DatabaseHandler.getProgrammeNameIfPercentageExists(pIDOut);

        return selectProgrammeList;
    }

    JPanel getEditWatched(){
        return this.content;
    }
}
