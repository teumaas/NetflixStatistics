package UserInterface;

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
    }

    public JPanel createSideBar(){
        SideBar sidebar = new SideBar();
        return sidebar.getSideBar();
    }

    public JPanel createFooter(){
        Footer footer = new Footer();
        return footer.getFooter();
    }

    public JFrame getFrame(){
        return frame;
    }
}
