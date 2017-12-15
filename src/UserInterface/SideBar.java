package UserInterface;

import javax.swing.*;
import java.awt.*;

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

        JButton add = new JButton("Toevoegen");
        JButton alter = new JButton("Wijzigen");
        JButton delete = new JButton("Verwijderen");
        JButton percentageGeneral = new JButton("Gemiddeld % per aflevering");
        JButton percentageAccount = new JButton("Gemiddeld % per account");
        JButton moviesWatched = new JButton("Films bekeken per account");
        JButton longestTime = new JButton("Langste film voor onder 16");
        JButton oneProfile = new JButton("Accounts met één profiel");
        JButton fullyWatched = new JButton("Hoeveel keer afgekeken");

        add.setMargin(new Insets(0,0,0,100));

        navbar.add(add);
        navbar.add(alter);
        navbar.add(delete);
        navbar.add(percentageGeneral);
        navbar.add(percentageAccount);
        navbar.add(moviesWatched);
        navbar.add(moviesWatched);
        navbar.add(longestTime);
        navbar.add(oneProfile);
        navbar.add(fullyWatched);

        return navbar;
    }

    public JPanel createInformation(){
        JPanel information = new JPanel();

        JLabel greetings = new JLabel("Welkom");

        information.add(greetings);

        return information;
    }

    public JPanel getSideBar(){
        return this.sideBar;
    }
}
