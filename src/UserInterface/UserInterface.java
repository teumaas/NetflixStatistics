package UserInterface;

import UserInterface.Delete.DeleteLandingPage;
import UserInterface.Edit.EditLandingPage;
import UserInterface.New.NewLandingPage;

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
        container.add(createAveragePercentageEpisode());
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
