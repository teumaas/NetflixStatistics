package UserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AveragePercentageAccount {
    private JPanel content;

    public AveragePercentageAccount(){
        this.content = new JPanel();
        GridBagLayout layout = new GridBagLayout();

        GridBagConstraints constraints = new GridBagConstraints();
        this.content.setLayout(layout);

        String[] accountSelect = {"-Selecteer account-"};
        JComboBox selectList = new JComboBox(accountSelect);
        constraints.gridx = 0;
        constraints.gridy = 0;
        this.content.add(selectList, constraints);

        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                ItemSelectable is = (ItemSelectable)actionEvent.getSource();
                selectedString(is);
            }
        };
        selectList.addActionListener(actionListener);

        JLabel averagePercentage = new JLabel("Gemiddeld percentage bekeken: ");
        constraints.gridx = 0;
        constraints.gridy = 1;
        this.content.add(averagePercentage, constraints);
    }

    private void selectedString(ItemSelectable is) {
        Object selected[] = is.getSelectedObjects();
        String selectedValue = ((String)selected[0]);
    }

    public JPanel getAveragePercentageAccount(){
        return this.content;
    }
}
