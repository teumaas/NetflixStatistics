import Utillities.DatabaseHandler;
import UserInterface.UserInterface;

import javax.swing.*;

public class Main {

    public static DatabaseHandler databaseHandler;

    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        SwingUtilities.invokeLater(ui);

        databaseHandler = new DatabaseHandler("NetflixStatistix", true);
        databaseHandler.connect();
        databaseHandler.accountsWithOneProfile();
    }
}
