package UserInterface;

import Utillities.DatabaseHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

class AveragePercentageAccount {
    private JPanel content;
    private Map<Integer, String> selectAccountList;
    private JComboBox selectAccount;
    private GridBagConstraints constraints = new GridBagConstraints();


    AveragePercentageAccount(){
        GridBagLayout layout = new GridBagLayout();
        this.content = new JPanel();
        this.content.setLayout(layout);

        addSelector();
    }

    private void addSelector(){

        selectAccountList = DatabaseHandler.getAccountName();
        selectAccount = new JComboBox(selectAccountList.values().toArray());
        this.constraints.gridx = 0;
        constraints.gridy = 0;
        this.content.add(selectAccount, constraints);

        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                ItemSelectable is = (ItemSelectable)actionEvent.getSource();
                selectedString(is);
            }
        };
        selectAccount.addActionListener(actionListener);
    }

    public void addPercentage(String sqlValue){
        this.content.removeAll();

        addSelector();

        JLabel averagePercentage = new JLabel("Gemiddeld percentage bekeken: " + DatabaseHandler.getPercentageAccounts(sqlValue));
        constraints.gridx = 0;
        constraints.gridy = 1;
        this.content.add(averagePercentage, constraints);

        this.content.repaint();
        this.content.revalidate();
    }

    private void selectedString(ItemSelectable is) {
        Object selected[] = is.getSelectedObjects();
        String selectedValue = ((String)selected[0]);
        addPercentage(selectedValue);
    }

    JPanel getAveragePercentageAccount(){
        return this.content;
    }
}
