package Utillities;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class DatabaseHandler {
    private static String connectionURL;

    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;
    private static PreparedStatement preparedStatement = null;


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

    public static Map getAccountName() {
        Map<Integer,String> accounts = new HashMap<Integer,String>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Abonnee;");

            while (resultSet.next()) {
                accounts.put(resultSet.getInt("AbonneeID"), resultSet.getString("Naam"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accounts;
    }

    public static ArrayList getAccountInfo(int ID) {
        ArrayList<String> accountInfo = new ArrayList<String>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Abonnee WHERE AbonneeID = " + ID +";");

            while (resultSet.next()) {
                accountInfo.add(resultSet.getString("Naam"));
                accountInfo.add(resultSet.getString("Adres"));
                accountInfo.add(resultSet.getString("Huisnummer"));
                accountInfo.add(resultSet.getString("Postcode"));
                accountInfo.add(resultSet.getString("Woonplaats"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accountInfo;
    }

    public static ArrayList updateAccountInfo(ArrayList info) {
        ArrayList<String> accountInfo = info;

        String abonneeID = accountInfo.get(0);
        String name = accountInfo.get(1);
        String address = accountInfo.get(2);
        String houseNumber = accountInfo.get(3);
        String postalCode = accountInfo.get(4);
        String city = accountInfo.get(5);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Abonnee SET Naam = ?, Adres = ?, Huisnummer = ?, Postcode = ?, Woonplaats = ? WHERE AbonneeID = ?");

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, address);
            preparedStatement.setString(3, houseNumber);
            preparedStatement.setString(4, postalCode);
            preparedStatement.setString(5, city);
            preparedStatement.setString(6, abonneeID);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return accountInfo;
    }

    public static ArrayList setAccountInfo(ArrayList info) {
        ArrayList<String> accountInfo = info;

        String abonneeID = accountInfo.get(0);
        String name = accountInfo.get(1);
        String address = accountInfo.get(2);
        String houseNumber = accountInfo.get(3);
        String postalCode = accountInfo.get(4);
        String city = accountInfo.get(5);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Abonnee (AbonneeID, Naam, Adres, Huisnummer, Postcode, Woonplaats) VALUES (?, ?, ?, ?, ?, ?)");

            preparedStatement.setInt(1, Integer.parseInt(abonneeID));
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, address);
            preparedStatement.setString(4, houseNumber);
            preparedStatement.setString(5, postalCode);
            preparedStatement.setString(6, city);


            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return accountInfo;
    }

    public static void delete (String table, String idName, int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM " + table + " WHERE " + idName + " = ?;");

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}