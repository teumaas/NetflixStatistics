package UserInterface;

import UserInterface.Edit.EditLandingPage;

import javax.swing.*;
import java.awt.*;

public class UserInterface implements Runnable{
    private JFrame frame;

    public UserInterface(){
    }

    @Override
    public void run(){
        frame = new JFrame("NetflixStatistix");
        frame.setPreferredSize(new Dimension(800, 800));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents(Container container){
        container.add(createSideBar(), BorderLayout.WEST);
        container.add(createFooter(), BorderLayout.SOUTH);
        container.add(createEditLandingPage());
    }

    public JPanel createSideBar(){
        SideBar sidebar = new SideBar();
        return sidebar.getSideBar();
    }

    public JPanel createFooter(){
        Footer footer = new Footer();
        return footer.getFooter();
    }

    public JPanel createLandingPage(){
        LandingPage landingpage = new LandingPage();
        return landingpage.getLandingPage();
    }

    public JPanel createEditLandingPage(){
        EditLandingPage landingpage = new EditLandingPage();
        return landingpage.getEditLandingPage();
    }

    public JFrame getFrame(){
        return frame;
    }
}
