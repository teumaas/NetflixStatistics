import java.sql.*;

public class DatabaseHandler {
    private String connectionURL;

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public DatabaseHandler(String databaseName, boolean integratedSecurity) {
        this.connectionURL = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=" + databaseName + ";integratedSecurity="+ integratedSecurity +";";
    }

    public void connect()  {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(this.connectionURL);

            System.out.println("Successfully connected!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testConnection()
    {
        connect();
    }
}
