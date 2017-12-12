public class Main {

    public DatabaseHandler databaseHandler;

    public static void main(String[] args) {

        DatabaseHandler databaseHandler = new DatabaseHandler("Bibliotheek", true);

        databaseHandler.testConnection();
    }
}
