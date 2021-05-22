package ru.dmitrymorel.bank_api_task.database;

import org.h2.tools.RunScript;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

public class DatabaseConfig {

    private final static String PROPERTIES_PATH = "/Users/a19188814/Desktop/bank_api_task/src/main/resources/db.properties";
    private final static String SQL_SCRIPT_PATH = "/Users/a19188814/Desktop/bank_api_task/src/main/resources/Script.sql";

    private static final Logger log = Logger.getLogger(DatabaseConfig.class.getName());

    public static Connection getConnection() {
        Connection connection = null;
        try (InputStream inputStream = new FileInputStream(PROPERTIES_PATH)) {
            Properties properties = new Properties();
            properties.load(inputStream);

            String dbUrl = properties.getProperty("db.url");
            String dbUsername = properties.getProperty("db.username");
            String dbPassword = properties.getProperty("db.password");
            String dbDriverClassName = properties.getProperty("db.driverClassName");

            Class.forName(dbDriverClassName);
            connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

        } catch (IOException | SQLException | ClassNotFoundException e) {
            throw new IllegalStateException();
        }
        log.info("connection to DB established");
        return connection;
    }

    public static void createTables() {
        try (Connection connection = DatabaseConfig.getConnection()) {
            RunScript.execute(connection, new FileReader(SQL_SCRIPT_PATH));
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
