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
    public String selected;
    private String selectedEpisode;


    AveragePercentageEpisode(){
        this.content = new JPanel();
        GridBagLayout layout = new GridBagLayout();

        this.content.setLayout(layout);
        addSeriesSelector();
    }

    private void addSeriesSelector(){
        ArrayList<String> selectSeriesList = DatabaseHandler.getSeries();
        JComboBox selectSeries = new JComboBox(selectSeriesList.toArray());
        constraints.gridx = 0;
        constraints.gridy = 0;
        this.content.add(selectSeries, constraints);

        if (this.selected != null){
            selectSeries.setSelectedItem(this.selected);
        }


        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                ItemSelectable is = (ItemSelectable)actionEvent.getSource();
                selectedString(is);
            }
        };
        selectSeries.addActionListener(actionListener);
    }

    private void selectedString(ItemSelectable is) {
        this.content.removeAll();
        addSeriesSelector();

        Object selected[] = is.getSelectedObjects();
        String selectedValue = ((String)selected[0]);
        addEpisodeSelector(selectedValue);
        this.selected = selectedValue;
    }

    public void addEpisodeSelector(String sqlValue){
        this.selected = sqlValue;
        ArrayList<String> selectEpisodeList = DatabaseHandler.getEpisodes(this.selected);
        JComboBox episodeSelecter = new JComboBox(selectEpisodeList.toArray());
        constraints.gridx = 0;
        constraints.gridy = 1;
        this.content.add(episodeSelecter, constraints);

        if (this.selectedEpisode != null) {
            episodeSelecter.setSelectedItem(this.selectedEpisode);
        }


        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                ItemSelectable is = (ItemSelectable)actionEvent.getSource();
                selectedEpisode(is);
            }
        };
        episodeSelecter.addActionListener(actionListener);

        this.content.repaint();
        this.content.revalidate();
    }

    private void selectedEpisode(ItemSelectable is) {
        Object selected[] = is.getSelectedObjects();
        String selectedValue = ((String)selected[0]);
        addStatistics(selectedValue);
        this.selectedEpisode = selectedValue;
    }

    private void addStatistics(String sqlValue){
        this.content.removeAll();
        addSeriesSelector();
        addEpisodeSelector(this.selected);

        JLabel averagePercentage = new JLabel("Gemiddeld percentage bekeken: " + DatabaseHandler.getAveragePercentageEpisode(sqlValue));
        constraints.gridx = 0;
        constraints.gridy = 2;
        this.content.add(averagePercentage, constraints);

        this.content.repaint();
        this.content.revalidate();
    }

    JPanel getAveragePercentageEpisode(){
        return this.content;
    }
}
