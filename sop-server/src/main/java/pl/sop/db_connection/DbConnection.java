package pl.sop.db_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {

    private static Connection connection = null;

    private DbConnection() {
    }

    public static Connection getDbConnection() {
        try {
            if (connection == null) {
                String url = "jdbc:postgresql://localhost:5432/sop";
                Properties properties = new Properties();
                properties.setProperty("user", "postgres");
                properties.setProperty("password", "postgres");
                connection = DriverManager.getConnection(url, properties);
                System.out.println("Połączenie z bazą ustanowione!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
