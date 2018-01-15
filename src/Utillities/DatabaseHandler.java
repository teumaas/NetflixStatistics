package Utillities;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    public static void delete (String table, String idName, int id, String idNameTwo, int idTwo) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM " + table + " WHERE " + idName + " = ? AND " + idNameTwo + " = ?;");

            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, idTwo);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e) {
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

    public static String getHighestID() {
        int ID = 0;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT AbonneeID FROM Abonnee WHERE AbonneeID = (SELECT max(AbonneeID) FROM Abonnee)");

            while (resultSet.next()) {
               ID = resultSet.getInt("AbonneeID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ID += 1;

        return Integer.toString(ID);
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

    public static Map getProfileName(int id) {
        Map<Integer,String> profiles = new HashMap<Integer,String>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Profiel WHERE AbonneeID = "+ id +";");

            while (resultSet.next()) {
                profiles.put(resultSet.getInt("ProfielID"), resultSet.getString("Naam"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return profiles;
    }

    public static Map getProgrammeName(int pid) {
        Map<Integer, String> programmas = new HashMap<Integer, String>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT Programma.Titel, ProfielProgramma.ProfielID, ProfielProgramma.ProgrammaID FROM Programma JOIN ProfielProgramma ON Programma.ProgrammaID = ProfielProgramma.ProgrammaID WHERE ProfielProgramma.ProfielID = " + pid + ";");

            while (resultSet.next()) {
                programmas.put(resultSet.getInt("ProgrammaID"), resultSet.getString("Titel"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return programmas;
    }

    public static ArrayList getAccountInformation(int ID) {
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

    public static ArrayList getProfileInformation(int ID) {
        ArrayList<String> profielInfo = new ArrayList<String>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Profiel WHERE ProfielID = " + ID +";");

            while (resultSet.next()) {
                profielInfo.add(resultSet.getString("Naam"));
                profielInfo.add(resultSet.getString("Geboortedatum"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return profielInfo;
    }

    public static Map getProgrammeParentage(int pid) {
        Map<Integer, String> programmas = new HashMap<Integer, String>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT Programma.Titel, ProfielProgramma.ProfielID, ProfielProgramma.ProgrammaID, ProfielProgramma.ProfielPercentage FROM Programma JOIN ProfielProgramma ON Programma.ProgrammaID = ProfielProgramma.ProgrammaID WHERE ProfielProgramma.ProfielID = " + pid + ";");

            while (resultSet.next()) {
                programmas.put(resultSet.getInt("ProgrammaID"), resultSet.getString("ProfielPercentage"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return programmas;
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

    public static ArrayList updatePercentage(ArrayList info) {
        ArrayList<String> profielInfo = info;

        String profielID = profielInfo.get(0);
        String percentage = profielInfo.get(1);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE ProfielProgramma SET ProfielPercentage = ? WHERE ProfielID = ?;");

            preparedStatement.setString(1, percentage);
            preparedStatement.setInt(2, Integer.parseInt(profielID));

            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return profielInfo;
    }

    public static ArrayList setProfileInfo(ArrayList info) throws ParseException {
        ArrayList<String> profileInfo = info;

        String AbonneeID = profileInfo.get(0);
        String name = profileInfo.get(1);
        String dateOfBirth = profileInfo.get(2);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Profiel (AbonneeID, Naam, Geboortedatum) VALUES (?, ?, ?)");

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date parsed = format.parse(dateOfBirth);
            java.sql.Date cd = new java.sql.Date(parsed.getTime());

            preparedStatement.setString(1, AbonneeID);
            preparedStatement.setString(2, name);
            preparedStatement.setDate(3, cd);


            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return profileInfo;
    }



    public static ArrayList updateProfileInfo(ArrayList info) throws ParseException {
        ArrayList<String> accountInfo = info;

        String profileID = accountInfo.get(0);
        String name = accountInfo.get(1);
        String dateOfBirth = accountInfo.get(2);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Profiel SET Naam = ?, Geboortedatum = ? WHERE ProfielID = ?");

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date parsed = format.parse(dateOfBirth);
            java.sql.Date cd = new java.sql.Date(parsed.getTime());

            preparedStatement.setString(1, name);
            preparedStatement.setDate(2, cd);
            preparedStatement.setString(3, profileID);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e) {
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

    public static String longestMovieBelowSixteen (){
        String returnValue = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT TOP 1 Titel\n" +
                    "FROM Programma\n" +
                    "JOIN Film\n" +
                    "ON Programma.ProgrammaID = Film.ProgrammaID\n" +
                    "WHERE LeeftijdsIndicatie < 16\n" +
                    "ORDER BY Tijdsduur DESC;");

                while (resultSet.next()){
                    returnValue = resultSet.getString("Titel");
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnValue;
    }

    public static String accountsWithOneProfile(){
        String returnValue = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT COUNT(*) AS Resultaat FROM\n" +
                    "\t(SELECT AbonneeID\n" +
                    "\tFROM Profiel\n" +
                    "\tGROUP BY AbonneeID\n" +
                    "\tHAVING COUNT(AbonneeID) = 1) AS AbonneeID;");

            while (resultSet.next()){
                returnValue = resultSet.getString("Resultaat");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return returnValue;
    }

    public static HashMap getMovies(){
        HashMap<String, Integer> returnValue = new HashMap<String, Integer>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT Titel, Programma.ProgrammaID\n" +
                    "FROM Programma\n" +
                    "JOIN Film\n" +
                    "ON Film.ProgrammaID = Programma.ProgrammaID;");

            while (resultSet.next()){
                returnValue.put(resultSet.getString("Titel"), resultSet.getInt("ProgrammaID"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return returnValue;
    }

    public static ArrayList getMoviesWatchedPerAccount(String sqlValue){
        ArrayList<String > returnValue = new ArrayList<String>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT DISTINCT Programma.Titel\n" +
                    "FROM ProfielProgramma\n" +
                    "JOIN Programma\n" +
                    "ON ProfielProgramma.ProgrammaID = Programma.ProgrammaID\n" +
                    "JOIN Profiel\n" +
                    "ON ProfielProgramma.ProfielID = Profiel.ProfielID\n" +
                    "JOIN Abonnee\n" +
                    "ON Profiel.AbonneeID = Abonnee.AbonneeID\n" +
                    "WHERE Abonnee.Naam = '"+ sqlValue +"';");

            while (resultSet.next()){
                returnValue.add(resultSet.getString("Titel"));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return returnValue;
    }

    public static String getPercentageAccounts(String sqlValue){
        String returnValue = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT AVG(ProfielPercentage) AS AveragePercentage\n" +
                    "FROM ProfielProgramma\n" +
                    "JOIN Profiel\n" +
                    "ON ProfielProgramma.ProfielID = Profiel.ProfielID\n" +
                    "JOIN Abonnee\n" +
                    "ON Profiel.AbonneeID = Abonnee.AbonneeID\n" +
                    "WHERE Abonnee.Naam = '"+ sqlValue +"';");

            while (resultSet.next()){
                returnValue = resultSet.getString("AveragePercentage");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return returnValue;
    }

    public static ArrayList getSeries(){
        ArrayList<String> returnValue = new ArrayList<String>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT SerieTitel\n" +
                    "FROM Serie;");

            while (resultSet.next()){
                returnValue.add(resultSet.getString("SerieTitel"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return returnValue;
    }

    public static ArrayList getEpisodes(String sqlValue){
        ArrayList<String> returnValue = new ArrayList<String>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT Titel\n" +
                    "FROM Programma\n" +
                    "JOIN Aflevering\n" +
                    "ON Programma.ProgrammaID = Aflevering.AfleveringID\n" +
                    "JOIN Serie\n" +
                    "ON Aflevering.SerieID = Serie.SerieID\n" +
                    "WHERE SerieTitel = '"+ sqlValue +"';");

            while (resultSet.next()){
                returnValue.add(resultSet.getString("Titel"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return returnValue;
    }

    public static String getMoviesFullyWatched(String sqlValue){
        String returnValue = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT COUNT(ProfielPercentage) AS TimesWatched\n" +
                    "FROM ProfielProgramma\n" +
                    "JOIN Programma\n" +
                    "ON ProfielProgramma.ProgrammaID = Programma.ProgrammaID\n" +
                    "WHERE ProfielPercentage = '100' AND Programma.Titel = '"+ sqlValue +"';");

            while (resultSet.next()){
                returnValue = resultSet.getString("TimesWatched");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return returnValue;
    }

    public static String getAveragePercentageEpisode(String sqlValue){
        String returnValue = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT AVG(ProfielPercentage) AS AveragePercentage\n" +
                    "FROM ProfielProgramma\n" +
                    "JOIN Programma\n" +
                    "ON Programma.ProgrammaID = ProfielProgramma.ProgrammaID\n" +
                    "JOIN Aflevering\n" +
                    "ON Programma.Titel = Aflevering.AfleveringTitel\n" +
                    "GROUP BY Aflevering.Volgnummer, Programma.Titel\n" +
                    "HAVING Programma.Titel = '"+ sqlValue +"';");

            while (resultSet.next()){
                returnValue = resultSet.getString("AveragePercentage");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        if (returnValue == null) {
            return "0";
        } else {
            return returnValue;
        }
    }

    public static String getVolgnummer(String sqlValue){
        String returnValue = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT Aflevering.Volgnummer\n" +
                    "FROM Aflevering\n" +
                    "JOIN Programma\n" +
                    "ON Programma.Titel = Aflevering.AfleveringTitel\n" +
                    "GROUP BY Aflevering.Volgnummer, Programma.Titel\n" +
                    "HAVING Programma.Titel = '"+ sqlValue +"';");
            while (resultSet.next()){
                returnValue = resultSet.getString("Volgnummer");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return returnValue;
    }

    public static ArrayList getProfiles(String sqlValue){
        ArrayList<String> returnValue = new ArrayList<String>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT Profiel.Naam\n" +
                    "FROM Profiel\n" +
                    "JOIN Abonnee\n" +
                    "ON Profiel.AbonneeID = Abonnee.AbonneeID\n" +
                    "WHERE Abonnee.Naam = '"+ sqlValue +"';");

            while (resultSet.next()){
                returnValue.add(resultSet.getString("Naam"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return returnValue;
    }

    public static ArrayList getAllProgrammes(){
        ArrayList<String> returnValue = new ArrayList<String>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT Titel\n" +
                    "FROM Programma;");

            while (resultSet.next()){
                returnValue.add(resultSet.getString("Titel"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return returnValue;
    }

    public static ArrayList getProgrammesWatched(String nameValue, String profileValue) {
        ArrayList<String> returnValue = new ArrayList<String>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT Titel\n" +
                    "FROM Programma\n" +
                    "JOIN ProfielProgramma\n" +
                    "ON Programma.ProgrammaID = ProfielProgramma.ProgrammaID\n" +
                    "JOIN Profiel\n" +
                    "ON Profiel.ProfielID = ProfielProgramma.ProfielID\n" +
                    "JOIN Abonnee\n" +
                    "ON Abonnee.AbonneeID = Profiel.AbonneeID\n" +
                    "WHERE Abonnee.Naam = '" + nameValue + "' AND Profiel.Naam = '" + profileValue + "';");

            while (resultSet.next()) {
                returnValue.add(resultSet.getString("Titel"));
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return returnValue;
    }

    public static String getPercentage(String abonneeNaam, String profielNaam, String titel){
        String returnValue = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT ProfielPercentage\n" +
                    "FROM ProfielProgramma\n" +
                    "JOIN Programma\n" +
                    "ON Programma.ProgrammaID = ProfielProgramma.ProgrammaID\n" +
                    "JOIN Profiel \n" +
                    "ON Profiel.ProfielID = ProfielProgramma.ProfielID\n" +
                    "JOIN Abonnee\n" +
                    "ON Profiel.AbonneeID = Abonnee.AbonneeID\n" +
                    "WHERE Profiel.Naam = '"+ abonneeNaam +"' AND Abonnee.Naam = '"+ profielNaam +"' AND Programma.Titel = '"+ titel +"';");

            while (resultSet.next()){
                returnValue = resultSet.getString("ProfielPercentage");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return returnValue;
    }
}