package pl.sop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.sop.db_connection.DbConnection;

import java.sql.Connection;

@SpringBootApplication
public class SopApplication {
    public static void main(String[] args) {
        SpringApplication.run(SopApplication.class, args);
        Connection connection = DbConnection.getDbConnection();
    }
}
