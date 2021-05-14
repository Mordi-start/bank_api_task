package ru.dmitrymorel.bank_api_task.dao;

import ru.dmitrymorel.bank_api_task.model.User;

import java.sql.*;
import java.util.List;

public class UserDAO implements CrudDAO<User>{

    static final String URL = "jdbc:h2:~/database";
    static final String USER = "user";
    static final String PASSWORD = "password";

    private static Connection connection;

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

    @Override
    public User get(int id) {
        User user = null;

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "SELECT * FROM USERS " +
                                    "WHERE ID=?");

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            user = new User();

            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setSurname(resultSet.getString("surname"));


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void save(User user) {

    }

    @Override
    public void update(int id, User user) {

    }

    @Override
    public void delete(int id) {

    }
}
