package ru.dmitrymorel.bank_api_task.dao.dao_interfaces;

import ru.dmitrymorel.bank_api_task.model.Account;

import java.math.BigDecimal;

public interface AccountDAO {
    void saveAccount(Account account);

    void addMoney(int cardId, BigDecimal value);

    void withdrawMoney(int cardId, BigDecimal value);

    void transaction(int sendingCardId, int gettingCardId, BigDecimal value);

    BigDecimal getBalance(int cardId);

    void changeAccountStatus(int accountId);
}
