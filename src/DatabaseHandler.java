import java.sql.*;

public class DatabaseHandler {

    private String databaseName;
    private String connectionURL;
    private boolean integratedSecurity;

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public DatabaseHandler(String databaseName, boolean integratedSecurity) {
        this.databaseName = databaseName;
        this.integratedSecurity = integratedSecurity;

        this.connectionURL = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=" + this.databaseName + ";integratedSecurity="+ this.integratedSecurity +";";
    }

    public void connect()  {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(this.connectionURL);

            System.out.println("Successfully connected!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testConnection()
    {
        connect();
    }
}
