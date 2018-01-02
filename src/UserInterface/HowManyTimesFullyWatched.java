package UserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class HowManyTimesFullyWatched {
    private JPanel content;

    HowManyTimesFullyWatched(){
        this.content = new JPanel();
        GridBagLayout layout = new GridBagLayout();

        GridBagConstraints constraints = new GridBagConstraints();
        this.content.setLayout(layout);

        String[] moviesSelect = {"-Selecteer film-"};
        JComboBox selectList = new JComboBox(moviesSelect);
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

        JLabel timesWatched = new JLabel("Aantal keer volledig bekeken: ");
        constraints.gridx = 0;
        constraints.gridy = 1;
        this.content.add(timesWatched, constraints);
    }

    private void selectedString(ItemSelectable is) {
        Object selected[] = is.getSelectedObjects();
        String selectedValue = ((String)selected[0]);
    }

    JPanel getHowManyTimesFullyWatched(){
        return this.content;
    }
}

