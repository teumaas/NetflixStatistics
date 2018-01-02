package Utillities;

import java.sql.*;
import java.util.ArrayList;


public class DatabaseHandler {
    private static String connectionURL;

    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;

    public DatabaseHandler(String databaseName, boolean integratedSecurity) {
        connectionURL = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=" + databaseName + ";integratedSecurity="+ integratedSecurity +";";
    }

    public static void connect()  {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(connectionURL);

            System.out.println("Successfully connected!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void disconnect() {
        if (resultSet != null) try { resultSet.close(); } catch(Exception e) {}
        if (statement != null) try { statement.close(); } catch(Exception e) {}
        if (connection != null) try { connection.close(); } catch(Exception e) {}
    }

    public static void testConnection()
    {
        connect();
    }

    public static ArrayList getAccounts() {

        ArrayList<String> accounts = new ArrayList<String>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Abonnee;");

            while (resultSet.next()) {
                accounts.add(resultSet.getString("AbonneeID"));
                accounts.add(resultSet.getString("Naam"));
                accounts.add(resultSet.getString("Adres"));
                accounts.add(resultSet.getString("Huisnummer"));
                accounts.add(resultSet.getString("Postcode"));
                accounts.add(resultSet.getString("Woonplaats"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    return accounts;
    }
}
