package ru.dmitrymorel.bank_api_task.dao;

import ru.dmitrymorel.bank_api_task.database.DatabaseConfig;
import ru.dmitrymorel.bank_api_task.model.Account;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO /*implements CrudDAO<Account>*/ {

    private static final Connection connection = DatabaseConfig.getConnection();
    private final CardDAO cardDAO = new CardDAO();

//    @Override
//    public Account get(int id) {
//        Account account = null;
//
//        try {
//            PreparedStatement preparedStatement =
//                    connection.prepareStatement(
//                            "SELECT * FROM ACCOUNTS " +
//                                    "WHERE ID=?");
//
//            preparedStatement.setInt(1, id);
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            resultSet.next();
//
//            account = new Account();
//
//            account.setId(resultSet.getInt("id"));
//            account.setNumber(resultSet.getString("number"));
//            account.setBalance(resultSet.getBigDecimal("balance"));
//            account.setUserId(resultSet.getInt("user_id"));
//
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return account;
//    }

    public BigDecimal getBalance(int id) {
        BigDecimal balance = BigDecimal.valueOf(0.0);

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "SELECT BALANCE FROM ACCOUNTS " +
                                    "WHERE ID=" +
                                    "(SELECT ACCOUNT_ID FROM CARDS " +
                                    "WHERE ID =?)");

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            balance = BigDecimal.valueOf(resultSet.getDouble("balance"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return balance;
    }

//    @Override
//    public List<Account> getAll() {
//        List<Account> accounts = new ArrayList<>();
//
//        try {
//            PreparedStatement statement = connection
//                    .prepareStatement("SELECT * FROM accounts");
//
//            ResultSet resultSet = statement.executeQuery();
//
//            while (resultSet.next()) {
//                Account account = new Account();
//
//                account.setId(resultSet.getInt("id"));
//                account.setNumber(resultSet.getString("number"));
//                account.setBalance(resultSet.getBigDecimal("balance"));
//                account.setUserId(resultSet.getInt("user_id"));
//
//                accounts.add(account);
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//
//        return accounts;
//    }

    public List<Account> getAllForUser(int userId) {
        List<Account> accounts = new ArrayList<>();

        try {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT * FROM ACCOUNTS WHERE USER_ID=?");

            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Account account = new Account();

                account.setId(resultSet.getInt("id"));
                account.setNumber(resultSet.getString("number"));
                account.setBalance(resultSet.getBigDecimal("balance"));
                account.setUserId(resultSet.getInt("user_id"));
                account.setEnabled(resultSet.getBoolean("enabled"));

                accounts.add(account);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return accounts;
    }

//    @Override
//    public void save(Account account) {
//        try {
//            PreparedStatement preparedStatement =
//                    connection.prepareStatement("INSERT INTO accounts " +
//                            "VALUES(DEFAULT,?,?,?)");
//
//            preparedStatement.setString(1, account.getNumber());
//            preparedStatement.setDouble(2, account.getBalance());
//            preparedStatement.setInt(3, account.getUserId());
//
//            preparedStatement.executeUpdate();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
//
//    @Override
//    public void update(int id, Account account) {
//        try {
//            PreparedStatement preparedStatement =
//                    connection.prepareStatement("UPDATE accounts SET NUMBER=?, " +
//                            "BALANCE=?, USER_ID=? WHERE ID =?");
//
//            preparedStatement.setString(1, account.getNumber());
//            preparedStatement.setDouble(2, account.getBalance());
//            preparedStatement.setInt(3, account.getUserId());
//
//            preparedStatement.executeUpdate();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }

    public void addMoney(int cardId, BigDecimal value) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE accounts SET " +
                            "BALANCE=? WHERE ID = " +
                            "(SELECT ACCOUNT_ID FROM CARDS WHERE ID =?)");

            BigDecimal balance = getBalance(cardId);

            preparedStatement.setBigDecimal(1, balance.add(value));
            preparedStatement.setInt(2, cardId);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void withdrawMoney (int cardId, BigDecimal value) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE accounts SET " +
                            "BALANCE=? WHERE ID =" +
                            "(SELECT ACCOUNT_ID FROM CARDS WHERE ID =?)");

            BigDecimal balance = getBalance(cardId);

            if (balance.compareTo(value) == -1) {
                throw new SQLException("Баланс меньше указанной суммы");
            }
            preparedStatement.setBigDecimal(1, balance.subtract(value));
            preparedStatement.setInt(2, cardId);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void transaction(int sendAccountId, int gettingAccountId, BigDecimal value) {
        withdrawMoney(sendAccountId, value);
        addMoney(gettingAccountId, value);
    }

//    @Override
//    public void delete(int id) {
//        PreparedStatement preparedStatement = null;
//
//        try {
//            preparedStatement = connection.prepareStatement("DELETE FROM accounts " +
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
