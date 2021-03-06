package ru.dmitrymorel.bank_api_task.service;

import ru.dmitrymorel.bank_api_task.dao.AccountDAO;
import ru.dmitrymorel.bank_api_task.dao.CardDAO;
import ru.dmitrymorel.bank_api_task.model.Account;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class AccountService /*implements CrudService<Account>*/{

    private static final AccountDAO accountDAO = new AccountDAO();
    private static final CardDAO cardDAO = new CardDAO();

//    @Override
//    public Account get(int id) {
//        return accountDAO.get(id);
//    }

    public BigDecimal getBalance (int id) {
        return accountDAO.getBalance(id);
    }

//    @Override
//    public List<Account> getAll() {
//        return accountDAO.getAll();
//    }

    public List<Account> getAllForUser(int userId) {
        return accountDAO.getAllForUser(userId);
    }

//    @Override
//    public void save(Account account) {
//        accountDAO.save(account);
//    }
//
//    @Override
//    public void update(int id, Account account) {
//        accountDAO.update(id, account);
//    }

    public void addMoney(int cardId, BigDecimal value) {
        if (cardDAO.checkCardEnabled(cardId)) {
            accountDAO.addMoney(cardId, value);
        }
        else try {
            throw new SQLException();
        } catch (SQLException throwables) {
            System.out.println("Карта не активна");
        }
    }

    public void transaction(int sendCardId, int gettingCardId, BigDecimal value) {
        if (cardDAO.checkCardEnabled(sendCardId) && cardDAO.checkCardEnabled(gettingCardId)) {
            accountDAO.transaction(sendCardId, gettingCardId, value);
        }
        else try {
            throw new SQLException();
        } catch (SQLException throwables) {
            System.out.println("Одна из карт неактивна или несуществует");
        }
    }

    public void withdrawMoney(int cardId, BigDecimal value) {
        if (cardDAO.checkCardEnabled(cardId)) {
            accountDAO.withdrawMoney(cardId, value);
        }
        else try {
            throw new SQLException();
        } catch (SQLException throwables) {
            System.out.println("Карта не активна");
        }
    }

//        @Override
//    public void delete(int id) {
//        accountDAO.delete(id);
//    }
}
