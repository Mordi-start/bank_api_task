package ru.dmitrymorel.bank_api_task.dao;

import org.h2.jdbc.JdbcSQLNonTransientException;
import ru.dmitrymorel.bank_api_task.database.DatabaseConfig;
import ru.dmitrymorel.bank_api_task.model.Card;
import ru.dmitrymorel.bank_api_task.utils.CardNumberRandomizer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CardDAO /*implements CrudDAO<Card>*/ {
    private static final Connection connection = DatabaseConfig.getConnection();

//    @Override
//    public Card get(int id) {
//        Card card = null;
//        try {
//            PreparedStatement preparedStatement =
//                    connection.prepareStatement(
//                            "SELECT * FROM cards " +
//                                    "WHERE ID=?");
//
//            preparedStatement.setInt(1, id);
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            resultSet.next();
//
//            card = new Card();
//
//            card.setId(resultSet.getInt("id"));
//            card.setNumber(resultSet.getString("number"));
//            card.setAccountId(resultSet.getInt("account_id"));
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return card;
//    }

    public boolean checkCardNumber (String cardNumber) {
        String number = null;
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "SELECT number FROM cards " +
                                    "WHERE number=?");

            preparedStatement.setString(1, cardNumber);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                number = resultSet.getString("number");
            }

            if (!cardNumber.equals(number)) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

//    @Override
//    public List<Card> getAll() {
//        List<Card> cards = new ArrayList<>();
//        try {
//            PreparedStatement preparedStatement = connection
//                    .prepareStatement("SELECT * FROM cards");
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            while (resultSet.next()) {
//                Card card = new Card();
//
//                card.setId(resultSet.getInt("id"));
//                card.setNumber(resultSet.getString("number"));
//                card.setAccountId(resultSet.getInt("account_id"));
//
//                cards.add(card);
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return cards;
//    }

    public List<Card> getAllForAccount(int account_id) {
        List<Card> cards = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM CARDS WHERE ACCOUNT_ID = ?");

            preparedStatement.setInt(1, account_id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Card card = new Card();

                card.setId(resultSet.getInt("id"));
                card.setNumber(resultSet.getString("number"));
                card.setAccountId(resultSet.getInt("account_id"));
                card.setEnabled(resultSet.getBoolean("enabled"));

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
                    .prepareStatement("SELECT * FROM CARDS " +
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
                card.setAccountId(resultSet.getInt("account_id"));
                card.setEnabled(resultSet.getBoolean("enabled"));

                cards.add(card);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return cards;
    }

//    @Override
//    public void save(Card card) {
//        try {
//            PreparedStatement preparedStatement =
//                    connection.prepareStatement("INSERT INTO cards " +
//                            "VALUES(DEFAULT,?,?,?,?)");
//
//            preparedStatement.setString(1, card.getNumber());
//            preparedStatement.setInt(4, card.getAccountId());
//
//            preparedStatement.executeUpdate();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }

    public boolean checkAccountExists(int accountId) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT ID FROM ACCOUNTS " +
                            "WHERE ID=?");

            preparedStatement.setInt(1, accountId);

            ResultSet resultSet = preparedStatement.executeQuery();

            int id = 0;
            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }
            return id != 0;
        } catch (SQLException throwables) {
            System.out.println("Счет не существует");
        }
        return false;
    }

    public void saveForAccount(int account_id) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO cards(ID, NUMBER, ACCOUNT_ID) " +
                            "VALUES(DEFAULT,?,?)");

            String cardNumber = CardNumberRandomizer.randomNumber();

            if (!checkCardNumber(cardNumber)) { //Отдельный класс
                saveForAccount(account_id);
            }
            else {
                preparedStatement.setString(1, cardNumber);
                preparedStatement.setInt(2, account_id);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

//    @Override
//    public void update(int id, Card card) {
//        try {
//            PreparedStatement preparedStatement =
//                    connection.prepareStatement("UPDATE cards SET NUMBER=?, " +
//                            "TYPE=?, PAYMENT_SYSTEM=?, ACCOUNT_ID WHERE ID =?");
//
//            preparedStatement.setString(1, card.getNumber());
//            preparedStatement.setInt(3, card.getAccountId());
//
//            preparedStatement.executeUpdate();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
//
//    @Override
//    public void delete(int id) {
//        PreparedStatement preparedStatement = null;
//
//        try {
//            preparedStatement = connection.prepareStatement("DELETE FROM cards " +
//                    "WHERE ID=?");
//
//            preparedStatement.setInt(1, id);
//
//            preparedStatement.executeUpdate();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
}
