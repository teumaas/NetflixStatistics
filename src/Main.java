import Database.DatabaseHandler;
import UserInterface.UserInterface;

import javax.swing.*;

public class Main {

    public DatabaseHandler databaseHandler;

    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        SwingUtilities.invokeLater(ui);

        DatabaseHandler databaseHandler = new DatabaseHandler("NetflixStatistix", true);
        databaseHandler.testConnection();

        databaseHandler.getAccounts();
    }
}
