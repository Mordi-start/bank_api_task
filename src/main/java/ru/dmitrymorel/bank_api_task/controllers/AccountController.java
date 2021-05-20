package ru.dmitrymorel.bank_api_task.controllers;

import ru.dmitrymorel.bank_api_task.model.Account;
import ru.dmitrymorel.bank_api_task.service.AccountService;

import java.math.BigDecimal;
import java.util.List;

public class AccountController implements CrudController<Account>{

    private static final AccountService accountService = new AccountService();
    @Override
    public Account get(int id) {
        return accountService.get(id);
    }

    public BigDecimal getBalance (int id) {
        return accountService.getBalance(id);
    }

    @Override
    public List<Account> getAll() {
        return accountService.getAll();
    }

    @Override
    public void save(Account account) {
        accountService.save(account);
    }

    @Override
    public void update(int id, Account account) {
        accountService.update(id, account);
    }

    public void updateBalance(int id, BigDecimal income) {
        accountService.addMoney(id, income);
    }

    @Override
    public void delete(int id) {
        accountService.delete(id);
    }
}
