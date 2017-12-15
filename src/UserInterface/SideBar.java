package UserInterface;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SideBar{
    private JPanel sideBar;

    public SideBar(){
        this.sideBar = new JPanel();

        GridLayout layout = new GridLayout(2, 1);
        this.sideBar.setLayout(layout);

        this.sideBar.add(createInformation());
        this.sideBar.add(createNavbar());
    }

    public JToolBar createNavbar(){
        JToolBar navbar = new JToolBar("Navigatie", BoxLayout.Y_AXIS);

        ArrayList<JButton> button = createButtons();

        int i = 0;
        double width = 0;
        double margin = 0;
        int marginInt = 0;

        while (i < button.size()){
            navbar.add(button.get(i));
            if (width < button.get(i).getPreferredSize().getWidth()) {
                width = button.get(i).getPreferredSize().getWidth();
            }
            i++;
        }

        i=0;
        while (i < button.size()){
            margin = width - button.get(i).getPreferredSize().getWidth();
            marginInt = (int)margin;
            button.get(i).setMargin(new Insets(2,2,2,marginInt));
            i++;
        }
        return navbar;
    }

    public JPanel createInformation(){
        JPanel information = new JPanel();

        JLabel greetings = new JLabel("Welkom");

        information.add(greetings);

        return information;
    }

    public ArrayList<JButton> createButtons(){
        ArrayList<JButton> button = new ArrayList<JButton>();

        JButton add = new JButton("Toevoegen");
        button.add(add);
        JButton alter = new JButton("Wijzigen");
        button.add(alter);
        JButton delete = new JButton("Verwijderen");
        button.add(delete);
        JButton percentageGeneral = new JButton("Gemiddeld % per aflevering");
        button.add(percentageGeneral);
        JButton percentageAccount = new JButton("Gemiddeld % per account");
        button.add(percentageAccount);
        JButton moviesWatched = new JButton("Films bekeken per account");
        button.add(moviesWatched);
        JButton longestTime = new JButton("Langste film voor onder 16");
        button.add(longestTime);
        JButton oneProfile = new JButton("Accounts met één profiel");
        button.add(oneProfile);
        JButton fullyWatched = new JButton("Hoeveel keer afgekeken");
        button.add(fullyWatched);

        return button;
    }


    public JPanel getSideBar(){
        return this.sideBar;
    }
}
