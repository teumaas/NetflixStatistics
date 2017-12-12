package UserInterface;

import javax.swing.*;
import java.awt.*;

public class SideBar{
    private JPanel sideBar;

    public SideBar(){
        this.sideBar = new JPanel();

        GridLayout layout = new GridLayout(2, 1);
        this.sideBar.setLayout(layout);

        this.sideBar.add(createNavbar());
        this.sideBar.add(createInformation());
    }

    public JToolBar createNavbar(){
        JToolBar navbar = new JToolBar("Navigatie", BoxLayout.Y_AXIS);
        navbar.setFloatable(false);

        JButton button = new JButton("Optie één");

        navbar.add(button, BorderLayout.SOUTH);

        return navbar;
    }

    public JPanel createInformation(){
        JPanel information = new JPanel();

        JLabel greetings = new JLabel("Welkom!");

        information.add(greetings);

        return information;
    }

    public JPanel getSideBar(){
        return this.sideBar;
    }
}
