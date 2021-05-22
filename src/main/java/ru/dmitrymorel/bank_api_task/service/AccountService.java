package ru.dmitrymorel.bank_api_task.service;

import ru.dmitrymorel.bank_api_task.dao.AccountDAO;
import ru.dmitrymorel.bank_api_task.model.Account;

import java.math.BigDecimal;
import java.util.List;

public class AccountService /*implements CrudService<Account>*/{

    private static final AccountDAO accountDAO = new AccountDAO();

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
        accountDAO.addMoney(cardId, value);
    }

    public void transaction(int sendAccountId, int gettingAccountId, BigDecimal income) {
        accountDAO.transaction(sendAccountId, gettingAccountId, income);
    }

    public void withdrawMoney(int cardId, BigDecimal value) {
        accountDAO.withdrawMoney(cardId, value);
    }

//        @Override
//    public void delete(int id) {
//        accountDAO.delete(id);
//    }
}
