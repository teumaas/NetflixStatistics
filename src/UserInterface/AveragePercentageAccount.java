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
    private JLabel averagePercentage;

    //Constructor
    AveragePercentageAccount(){
        //Regelt content en layout
        GridBagLayout layout = new GridBagLayout();
        this.content = new JPanel();
        this.content.setLayout(layout);

        //Maakt een JComboBox met de waarden uit de database
        selectAccountList = DatabaseHandler.getAccountName();
        selectAccount = new JComboBox(selectAccountList.values().toArray());
        this.constraints.gridx = 0;
        constraints.gridy = 0;
        this.content.add(selectAccount, constraints);

        //Maakt een leeg JLabel aan
        averagePercentage = new JLabel("");
        constraints.gridx = 0;
        constraints.gridy = 1;
        this.content.add(averagePercentage, constraints);

        //Actionlistener
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                ItemSelectable is = (ItemSelectable)actionEvent.getSource();
                selectedString(is);
            }
        };

        //Voegt de actionlistener toe aan de JComboBox
        selectAccount.addActionListener(actionListener);
    }

    //Methode om de actionlistener af te handelen
    private void selectedString(ItemSelectable is) {
        //Krijgt de waarde van momenteel geselecteerd item uit JComboBOx
        Object selected[] = is.getSelectedObjects();
        String selectedValue = ((String)selected[0]);

        //Stelt de waarde in van de JLabel met een waarde uit de database
        averagePercentage.setText("Gemiddeld percentage bekeken:" + DatabaseHandler.getPercentageAccounts(selectedValue));

        //Herlaadt de pagina content
        this.content.repaint();
        this.content.revalidate();
    }

    //Getter voor deze pagina
    JPanel getAveragePercentageAccount(){
        return this.content;
    }
}
