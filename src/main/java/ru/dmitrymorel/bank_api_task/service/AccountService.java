package ru.dmitrymorel.bank_api_task.service;

import ru.dmitrymorel.bank_api_task.dao.AccountDAOImpl;
import ru.dmitrymorel.bank_api_task.dao.CardDAOImpl;
import ru.dmitrymorel.bank_api_task.model.Account;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class AccountService /*implements CrudService<Account>*/{

    private static final AccountDAOImpl ACCOUNT_DAO_IMPL = new AccountDAOImpl();
    private static final CardDAOImpl CARD_DAO_IMPL = new CardDAOImpl();

//    @Override
//    public Account get(int id) {
//        return accountDAO.get(id);
//    }

    public BigDecimal getBalance (int id) {
        return ACCOUNT_DAO_IMPL.getBalance(id);
    }

//    @Override
//    public List<Account> getAll() {
//        return accountDAO.getAll();
//    }

//    public List<Account> getAllForUser(int userId) {
//        return ACCOUNT_DAO_IMPL.getAllForUser(userId);
//    }

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
        if (CARD_DAO_IMPL.checkCardEnabled(cardId)) {
            ACCOUNT_DAO_IMPL.addMoney(cardId, value);
        }
        else try {
            throw new SQLException();
        } catch (SQLException throwables) {
            System.out.println("Карта не активна");
        }
    }

    public void transaction(int sendCardId, int gettingCardId, BigDecimal value) {
        if (CARD_DAO_IMPL.checkCardEnabled(sendCardId) && CARD_DAO_IMPL.checkCardEnabled(gettingCardId)) {
            ACCOUNT_DAO_IMPL.transaction(sendCardId, gettingCardId, value);
        }
        else try {
            throw new SQLException();
        } catch (SQLException throwables) {
            System.out.println("Одна из карт неактивна или несуществует");
        }
    }

    public void withdrawMoney(int cardId, BigDecimal value) {
        if (CARD_DAO_IMPL.checkCardEnabled(cardId)) {
            ACCOUNT_DAO_IMPL.withdrawMoney(cardId, value);
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
