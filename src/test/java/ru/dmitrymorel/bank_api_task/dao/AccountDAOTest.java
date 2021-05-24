package ru.dmitrymorel.bank_api_task.dao;

import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import ru.dmitrymorel.bank_api_task.database.DatabaseConfig;
import ru.dmitrymorel.bank_api_task.model.Account;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountDAOTest {

    private final AccountDAO accountDAO = new AccountDAO();

    @BeforeEach
    void beforeTest() {
        DatabaseConfig.createTables();
    }

    @Test
    void checkAccountExists() {
        boolean check = accountDAO.checkAccountExists(10);
        assertFalse(check);
    }

    @Test
    void checkAccountEnabled() {
        boolean enabledFalse = accountDAO.checkAccountEnabled(5);
        boolean enabledTrue = accountDAO.checkAccountEnabled(1);

        assertFalse(enabledFalse);
        assertTrue(enabledTrue);
    }

    @Test
    void getBalance() {
        BigDecimal expected = BigDecimal.valueOf(900000d);

        BigDecimal actual = accountDAO.getBalance(1);

        assertEquals(expected, actual);
    }

    @Test
    void getAllForUser() {
        List<Account> expected = new ArrayList<>();
        expected.add(new Account(1, "11111111111111111111", BigDecimal.valueOf(900000d), 1, true));
        expected.add(new Account(4, "44444444444444444444", BigDecimal.valueOf(99090d), 1, true));

        List<Account> actual = accountDAO.getAllForUser(1);

        assertEquals(expected, actual);
    }

    @Test
    void addMoney() {
        BigDecimal expected1 = accountDAO.getBalance(1).add(BigDecimal.valueOf(333.33));

        accountDAO.addMoney(1, BigDecimal.valueOf(333.33));

        BigDecimal actual1 = accountDAO.getBalance(1);

        assertEquals(expected1, actual1);
    }

    @Test
    void withdrawMoney() {
        BigDecimal expected1 = accountDAO.getBalance(1).subtract(BigDecimal.valueOf(333.33d));

        accountDAO.withdrawMoney(1, BigDecimal.valueOf(333.33d));

        BigDecimal actual1 = accountDAO.getBalance(1);

        assertEquals(expected1, actual1);
    }

    @Test
    void transaction() {
        BigDecimal value = BigDecimal.valueOf(10000d);
        int sendCardId = 1;
        int getCardId = 4;
        BigDecimal expectedSend = accountDAO.getBalance(sendCardId).subtract(value);
        BigDecimal expectedGet = accountDAO.getBalance(getCardId).add(value);

        accountDAO.transaction(sendCardId, getCardId, value);
        BigDecimal actualSend = accountDAO.getBalance(sendCardId);
        BigDecimal actualGet = accountDAO.getBalance(getCardId);

        assertEquals(expectedSend, actualSend);
        assertEquals(expectedGet, actualGet);
    }
}