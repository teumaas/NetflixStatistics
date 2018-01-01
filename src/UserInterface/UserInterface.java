package UserInterface;

import UserInterface.Delete.DeleteLandingPage;
import UserInterface.Edit.EditLandingPage;
import UserInterface.New.NewLandingPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UserInterface implements Runnable{
    protected JFrame frame;
    private JPanel content;

    public UserInterface(){
        this.content = new JPanel();

        GridBagLayout layout = new GridBagLayout();
        this.content.setLayout(layout);
    }

    @Override
    public void run(){
        frame = new JFrame("NetflixStatistix");
        frame.setPreferredSize(new Dimension(800, 800));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents();

        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents(){
        this.frame.add(SideBar(), BorderLayout.WEST);
        this.frame.add(createFooter(), BorderLayout.SOUTH);
    }

    public JPanel SideBar(){
        JPanel sidebar = new JPanel();

        GridLayout layoutSidebar = new GridLayout(2, 1);
        sidebar.setLayout(layoutSidebar);

        sidebar.add(createInformation());
        sidebar.add(createNavbar());

        return sidebar;
    }

    public JToolBar createNavbar(){
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                setContent(actionEvent.getActionCommand());
            }
        };

        JToolBar navbar = new JToolBar("Navigatie", BoxLayout.Y_AXIS);
        navbar.setFloatable(false);

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
            button.get(i).addActionListener(actionListener);
            i++;
        }
        return navbar;
    }

    private void setContent(String buttonValue){
        this.frame.getContentPane().removeAll();
        createComponents();

        switch (buttonValue){
            case "Toevoegen": this.frame.add(createNewLandingPage());
                break;
            case "Wijzigen": this.frame.add(createEditLandingPage());
                break;
            case "Verwijderen": this.frame.add(createDeleteLandingPage());
                break;
            case "Gemiddeld % per aflevering": this.frame.add(createAveragePercentageEpisode());
                break;
            case "Gemiddeld % per account": this.frame.add(createAveragePercentageAccount());
                break;
            case "Films bekeken per account": this.frame.add(createMoviesWatchedAccount());
                break;
            case "Langste film voor onder 16": this.frame.add(createLongestMovieBelowSixteen());
                break;
            case "Accounts met één profiel": this.frame.add(createAccountsOneProfile());
                break;
            case "Hoeveel keer afgekeken": this.frame.add(createHowManyTimesFullyWatched());
        }

        this.frame.revalidate();
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

    public JPanel createDeleteLandingPage(){
        DeleteLandingPage landingpage = new DeleteLandingPage();
        return landingpage.getDeleteLandingPage();
    }

    public JPanel createNewLandingPage(){
        NewLandingPage landingpage = new NewLandingPage();
        return landingpage.getNewLandingPage();
    }

    public JPanel createHowManyTimesFullyWatched(){
        HowManyTimesFullyWatched howmanytimesfullywatched = new HowManyTimesFullyWatched();
        return howmanytimesfullywatched.getHowManyTimesFullyWatched();
    }

    public JPanel createAccountsOneProfile(){
        AccountsOneProfile accountsoneprofile = new AccountsOneProfile();
        return accountsoneprofile.getAccountsOneProfile();
    }

    public JPanel createLongestMovieBelowSixteen(){
        LongestMovieBelowSixteen longestmoviebelowsixteen = new LongestMovieBelowSixteen();
        return longestmoviebelowsixteen.getLongestMovieBelowSixteen();
    }

    public JPanel createMoviesWatchedAccount(){
        MoviesWatchedAccount movieswatchedaccount = new MoviesWatchedAccount();
        return movieswatchedaccount.getMoviesWatchedAccount();
    }

    public JPanel createAveragePercentageAccount(){
        AveragePercentageAccount averagepercentageaccount = new AveragePercentageAccount();
        return averagepercentageaccount.getAveragePercentageAccount();
    }

    public JPanel createAveragePercentageEpisode(){
        AveragePercentageEpisode averagepercentageepisode = new AveragePercentageEpisode();
        return averagepercentageepisode.getAveragePercentageEpisode();
    }

    public JFrame getFrame(){
        return frame;
    }
}
