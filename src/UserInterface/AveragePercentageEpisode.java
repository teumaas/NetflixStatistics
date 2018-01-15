package UserInterface;

import Utillities.DatabaseHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class AveragePercentageEpisode {
    private JPanel content;
    private GridBagConstraints constraints = new GridBagConstraints();
    private GridBagLayout layout = new GridBagLayout();
    private JComboBox selectSeries;
    private JComboBox selectEpisode;
    private JLabel averagePercentage;

    //Constructor
    AveragePercentageEpisode(){
        //Stelt de content van de pagina in
        this.content = new JPanel();
        this.content.setLayout(layout);

        //Stelt de eerste JComboBox in
        ArrayList<String> selectSeriesList = DatabaseHandler.getSeries();
        selectSeries = new JComboBox(selectSeriesList.toArray());
        constraints.gridx = 0;
        constraints.gridy = 0;
        this.content.add(selectSeries, constraints);

        //Stelt de tweede JComboBox in
        String[] defaultValues = {"-Selecteer-"};
        selectEpisode = new JComboBox(defaultValues);
        constraints.gridx = 0;
        constraints.gridy = 1;
        this.content.add(selectEpisode, constraints);

        //Stelt een lege JLabel in
        averagePercentage = new JLabel("");
        constraints.gridx = 0;
        constraints.gridy = 2;
        this.content.add(averagePercentage, constraints);

        //Actionlistener voor de eerste JComboBox
        ActionListener actionListenerSeries = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                ItemSelectable is = (ItemSelectable)actionEvent.getSource();
                selectedSeries(is);
            }
        };

        //Voegt de Actionlistener toe
        selectSeries.addActionListener(actionListenerSeries);

        //Actionlistener voor de tweede JComboBox
        ActionListener actionListenerEpisode = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                ItemSelectable is = (ItemSelectable)actionEvent.getSource();
                selectedEpisode(is);
                System.out.println("Triggered");
            }
        };

        //Voegt de Actionlistener toe
        selectEpisode.addActionListener(actionListenerEpisode);
    }

    //Handelt de eerste actionListener af
    private void selectedSeries(ItemSelectable is) {
        //Pakt de value die geselecteerd is in de JComboBox en initialiseerd de ArrayList met waarden
        Object selected[] = is.getSelectedObjects();
        String selectedValue = ((String)selected[0]);
        ArrayList<String> values = DatabaseHandler.getEpisodes(selectedValue);

        //Verwijdert de oude JComboBox en maakt een nieuwe
        this.content.remove(selectEpisode);
        selectEpisode = new JComboBox();
        constraints.gridx = 0;
        constraints.gridy = 1;
        this.content.add(selectEpisode, constraints);

        //Maakt een nieuwe actionlistener aan voor de nieuwe JComboBox
        ActionListener actionListenerEpisode = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                ItemSelectable is = (ItemSelectable)actionEvent.getSource();
                selectedEpisode(is);
            }
        };

        //Voegt de nieuwe actionlistener toe aan de nieuwe JComboBox
        selectEpisode.addActionListener(actionListenerEpisode);

        //Voegt alle items toe aan de nieuwe JComboBox
        int i = 0;
        this.selectEpisode.removeAll();
        while (i < values.size()) {
            selectEpisode.addItem(values.get(i));
            i++;
        }

        //Herlaadt de content van deze pagina
        this.content.repaint();
        this.content.revalidate();
    }

    //Handelt de tweede actionlistener af
    private void selectedEpisode(ItemSelectable is) {
        //Pakt de waarde van het geselecteerde item in de JComboBox
        Object selected[] = is.getSelectedObjects();
        String selectedValue = ((String)selected[0]);

        //Stelt de tekst van het JLabel in op de juiste waarde
        averagePercentage.setText("Gemiddeld percentage bekeken: " + DatabaseHandler.getAveragePercentageEpisode(selectedValue));

        //Herlaadt de pagina
        this.content.repaint();
        this.content.revalidate();
    }

    //Getter voor de content
    JPanel getAveragePercentageEpisode(){
        return this.content;
    }
}
