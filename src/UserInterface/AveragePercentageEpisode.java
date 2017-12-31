package UserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AveragePercentageEpisode {
    private JPanel content;

    public AveragePercentageEpisode(){
        this.content = new JPanel();
        GridBagLayout layout = new GridBagLayout();

        GridBagConstraints constraints = new GridBagConstraints();
        this.content.setLayout(layout);

        String[] seriesSelect = {"-Selecteer serie-"};
        JComboBox selectList = new JComboBox(seriesSelect);
        constraints.gridx = 0;
        constraints.gridy = 0;
        this.content.add(selectList, constraints);

        String[] episodeSelect = {"-Selecteer aflevering-"};
        JComboBox episodeSelecter = new JComboBox(episodeSelect);
        constraints.gridx = 0;
        constraints.gridy = 1;
        this.content.add(episodeSelecter, constraints);

        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                ItemSelectable is = (ItemSelectable)actionEvent.getSource();
                selectedString(is);
            }
        };
        selectList.addActionListener(actionListener);

        JLabel averagePercentage = new JLabel("Gemiddeld percentage bekeken: ");
        constraints.gridx = 0;
        constraints.gridy = 2;
        this.content.add(averagePercentage, constraints);
    }

    private void selectedString(ItemSelectable is) {
        Object selected[] = is.getSelectedObjects();
        String selectedValue = ((String)selected[0]);
    }

    public JPanel getAveragePercentageEpisode(){
        return this.content;
    }
}
