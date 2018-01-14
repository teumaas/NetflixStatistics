package UserInterface;

import Utillities.DatabaseHandler;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class HowManyTimesFullyWatched {
    private JPanel content;
    private GridBagConstraints constraints = new GridBagConstraints();

    HowManyTimesFullyWatched(){
        this.content = new JPanel();
        GridBagLayout layout = new GridBagLayout();

        this.content.setLayout(layout);

        setSelectDropdown();
        setTimesWatched(null);
    }

    private void selectedString(ItemSelectable is) {
        Object selected[] = is.getSelectedObjects();
        String selectedValue = selected[0].toString();
        setTimesWatched(selectedValue);
    }

    private void setTimesWatched(String sqlValue){
        if (sqlValue == null){
            JLabel timesWatched = new JLabel("Aantal keer volledig bekeken: -");
            constraints.gridx = 0;
            constraints.gridy = 1;
            this.content.add(timesWatched, constraints);

            this.content.repaint();
        }
        else {
            this.content.removeAll();
            setSelectDropdown();

            JLabel timesWatched = new JLabel("Aantal keer volledig bekeken: " + DatabaseHandler.getMoviesFullyWatched(sqlValue));
            constraints.gridx = 0;
            constraints.gridy = 1;
            this.content.add(timesWatched, constraints);

            this.content.repaint();
            this.content.revalidate();
        }
    }

    private void setSelectDropdown(){
        JComboBox selectAccount = new JComboBox(DatabaseHandler.getMovies().keySet().toArray());
        constraints.gridx = 0;
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


    JPanel getHowManyTimesFullyWatched(){
        return this.content;
    }
}

