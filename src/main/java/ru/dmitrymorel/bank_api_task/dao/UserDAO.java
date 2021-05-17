package ru.dmitrymorel.bank_api_task.dao;

import ru.dmitrymorel.bank_api_task.model.User;
import ru.dmitrymorel.bank_api_task.utils.ConnectionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements CrudDAO<User>{
    private static final Connection connection = ConnectionBD.connection;

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
    public List<User> getAll() {
        List<User> users = new ArrayList<>();

        try {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT * FROM USERS");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                User user = new User();

                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));

                users.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return users;
    }

    @Override
    public void save(User user) {

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO USERS " +
                            "VALUES(DEFAULT,?,?)");

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(int id, User user) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE USERS SET NAME=?, " +
                            "SURNAME=? WHERE ID =?");

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setInt(3, user.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("DELETE FROM USERS " +
                    "WHERE ID=?");

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
