package UserInterface.Delete;

import Utillities.DatabaseHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


class DeleteProfiles {
    private JPanel content;
    private Map<Integer, String> selectAccountList;
    private Map<Integer, String> selectProfileList;
    private JComboBox selectAccount;
    private JComboBox selectProfile;
    private int AccountID;
    private int ProfileID;

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

        selectProfileList = new HashMap<Integer,String> ();
        selectProfileList.put(0, "--Selecteer profiel--");
        selectProfile = new JComboBox(loadProfiles().values().toArray());

        constraints.gridx = 0;
        constraints.gridy = 1;
        this.content.add(selectProfile, constraints);

        JButton submit = new JButton("Verwijder");
        constraints.gridx = 0;
        constraints.gridy = 2;
        this.content.add(submit, constraints);

        selectAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadJComboBox();
            }
        });


        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Map.Entry<Integer, String> entry : selectProfileList.entrySet()) {
                    if (entry.getValue().equals(selectProfile.getSelectedItem())) {
                        ProfileID = entry.getKey();

                        DatabaseHandler.delete("Profiel", "ProfielID", ProfileID);

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

    public Map loadProfiles() {
        for (Map.Entry<Integer, String> entry : selectAccountList.entrySet()) {
            if (entry.getValue().equals(selectAccount.getSelectedItem())) {
                AccountID = entry.getKey();
                selectProfileList = DatabaseHandler.getProfileName(AccountID);
            }
        }

        return selectProfileList;
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

    JPanel getDeleteprofiles(){
        return this.content;
    }
}
