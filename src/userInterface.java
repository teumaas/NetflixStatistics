import javax.swing.*;
import java.awt.*;

public class userInterface implements Runnable{
    private JFrame frame;

    public userInterface(){
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
        GridLayout layout = new GridLayout(1,5);
        container.setLayout(layout);

        JTextArea blokEen = new JTextArea("Blokje één");
        JTextArea blokTwee = new JTextArea("Blokje twee");
        JTextArea blokDrie = new JTextArea("Blokje drie");

        container.add(blokEen);
        container.add(blokTwee);
        container.add(blokDrie);
    }

    public JFrame getFrame(){
        return frame;
    }
}
