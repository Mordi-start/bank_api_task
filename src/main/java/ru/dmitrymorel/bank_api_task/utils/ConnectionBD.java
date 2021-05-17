package ru.dmitrymorel.bank_api_task.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBD {
    static final String URL = "jdbc:h2:~/database";
    static final String USER = "user";
    static final String PASSWORD = "password";

    public static Connection connection;

    static {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USER,
                    PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
