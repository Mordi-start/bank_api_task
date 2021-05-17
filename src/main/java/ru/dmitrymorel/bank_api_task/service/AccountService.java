package ru.dmitrymorel.bank_api_task.service;

import ru.dmitrymorel.bank_api_task.dao.AccountDAO;
import ru.dmitrymorel.bank_api_task.model.Account;

import java.util.List;

public class AccountService implements CrudService<Account>{

    private static final AccountDAO accountDAO = new AccountDAO();

    @Override
    public Account get(int id) {
        return accountDAO.get(id);
    }

    @Override
    public List<Account> getAll() {
        return accountDAO.getAll();
    }

    @Override
    public void save(Account account) {
        accountDAO.save(account);
    }

    @Override
    public void update(int id, Account account) {
        accountDAO.update(id, account);
    }

    @Override
    public void delete(int id) {
        accountDAO.delete(id);
    }
}
