package UserInterface.Delete;

import Utillities.DatabaseHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

class DeleteAccounts {
    private JPanel content;
    private Map<Integer, String> selectAccountList;
    private JComboBox selectAccount;
    private int abonneeID;

    DeleteAccounts(){
        this.content = new JPanel();
        GridBagLayout layout = new GridBagLayout();

        GridBagConstraints constraints = new GridBagConstraints();
        this.content.setLayout(layout);

        selectAccountList = DatabaseHandler.getAccountName();
        selectAccount = new JComboBox(selectAccountList.values().toArray());

        constraints.gridx = 0;
        constraints.gridy = 0;
        
        this.content.add(selectAccount, constraints);

        JButton submit = new JButton("Verwijder");
        constraints.gridx = 0;
        constraints.gridy = 1;
        this.content.add(submit, constraints);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Map.Entry<Integer, String> entry : selectAccountList.entrySet()) {
                    if (entry.getValue().equals(selectAccount.getSelectedItem())) {
                        abonneeID = entry.getKey();

                        DatabaseHandler.delete("Abonnee", "AbonneeID", abonneeID);

                        loadAbonnees();

                        content.revalidate();
                        content.repaint();
                    }
                }

            }
        });
    }

    public Map loadAbonnees() {
        for (Map.Entry<Integer, String> entry : selectAccountList.entrySet()) {
            if (entry.getValue().equals(selectAccount.getSelectedItem())) {
                selectAccountList = DatabaseHandler.getAccountName();
            }
        }

        return selectAccountList;
    }

    JPanel getDeleteAccounts(){
        return this.content;
    }
}
