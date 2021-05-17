package ru.dmitrymorel.bank_api_task.dao;

import ru.dmitrymorel.bank_api_task.model.Account;
import ru.dmitrymorel.bank_api_task.model.Card;
import ru.dmitrymorel.bank_api_task.model.User;
import ru.dmitrymorel.bank_api_task.utils.CardNumberRandomizer;
import ru.dmitrymorel.bank_api_task.utils.ConnectionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CardDAO implements CrudDAO<Card>{
    private static final Connection connection = ConnectionBD.connection;
    private static final AccountDAO accountDAO = new AccountDAO();

    @Override
    public Card get(int id) {
        Card card = null;
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "SELECT * FROM cards " +
                                    "WHERE ID=?");

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            card = new Card();

            card.setId(resultSet.getInt("id"));
            card.setNumber(resultSet.getString("number"));
            card.setType(resultSet.getString("type"));
            card.setPaymentSystem(resultSet.getString("payment_system"));
            card.setAccountId(resultSet.getInt("account_id"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return card;
    }

    @Override
    public List<Card> getAll() {
        List<Card> cards = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM cards");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Card card = new Card();

                card.setId(resultSet.getInt("id"));
                card.setNumber(resultSet.getString("number"));
                card.setType(resultSet.getString("type"));
                card.setPaymentSystem(resultSet.getString("payment_system"));
                card.setAccountId(resultSet.getInt("account_id"));

                cards.add(card);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return cards;
    }

    public List<Card> getAllForAccount(int account_id) {
        List<Card> cards = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM cards WHERE ACCOUNT_ID = ?");

            preparedStatement.setInt(1, account_id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Card card = new Card();

                card.setId(resultSet.getInt("id"));
                card.setNumber(resultSet.getString("number"));
                card.setType(resultSet.getString("type"));
                card.setPaymentSystem(resultSet.getString("payment_system"));
                card.setAccountId(resultSet.getInt("account_id"));

                cards.add(card);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return cards;
    }

    public List<Card> getAllForUser(int user_id) {
        List<Card> cards = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM cards " +
                            "JOIN ACCOUNTS " +
                            "ON CARDS.ACCOUNT_ID = ACCOUNTS.ID " +
                            "JOIN USERS " +
                            "ON ACCOUNTS.USER_ID = USERS.ID " +
                            "WHERE USER_ID = ?");

            preparedStatement.setInt(1, user_id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Card card = new Card();

                card.setId(resultSet.getInt("id"));
                card.setNumber(resultSet.getString("number"));
                card.setType(resultSet.getString("type"));
                card.setPaymentSystem(resultSet.getString("payment_system"));
                card.setAccountId(resultSet.getInt("account_id"));

                cards.add(card);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return cards;
    }

    @Override
    public void save(Card card) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO cards " +
                            "VALUES(DEFAULT,?,?,?,?)");

            preparedStatement.setString(1, card.getNumber());
            preparedStatement.setString(2, card.getType());
            preparedStatement.setString(3, card.getPaymentSystem());
            preparedStatement.setInt(4, card.getAccountId());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void saveForAccount(String type, String paymentSystem, int account_id) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO cards " +
                            "VALUES(DEFAULT,?,?,?,?)");

            preparedStatement.setString(1, CardNumberRandomizer.randomNumber());
            preparedStatement.setString(2, type);
            preparedStatement.setString(3, paymentSystem);
            preparedStatement.setInt(4, account_id);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(int id, Card card) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE cards SET NUMBER=?, " +
                            "TYPE=?, PAYMENT_SYSTEM=?, ACCOUNT_ID WHERE ID =?");

            preparedStatement.setString(1, card.getNumber());
            preparedStatement.setString(2, card.getType());
            preparedStatement.setString(3, card.getPaymentSystem());
            preparedStatement.setInt(3, card.getAccountId());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("DELETE FROM cards " +
                    "WHERE ID=?");

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}