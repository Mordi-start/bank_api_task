package ru.dmitrymorel.bank_api_task.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.dmitrymorel.bank_api_task.database.DatabaseConfig;
import ru.dmitrymorel.bank_api_task.model.Account;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountDAOImplTest {

    private final AccountDAOImpl accountDAOImpl = new AccountDAOImpl();

    @BeforeEach
    void beforeTest() {
        DatabaseConfig.createTables();
    }

    @Test
    void checkAccountExists() {
        boolean check = accountDAOImpl.checkAccountExists(10);
        assertFalse(check);
    }

    @Test
    void checkAccountEnabled() {
        boolean enabledFalse = accountDAOImpl.checkAccountEnabled(5);
        boolean enabledTrue = accountDAOImpl.checkAccountEnabled(1);

        assertFalse(enabledFalse);
        assertTrue(enabledTrue);
    }

    @Test
    void getBalance() {
        BigDecimal expected = BigDecimal.valueOf(900000d);

        BigDecimal actual = accountDAOImpl.getBalance(1);

        assertEquals(expected, actual);
    }

    @Test
    void getAllForUser() {
        List<Account> expected = new ArrayList<>();
        expected.add(new Account(1, "11111111111111111111", BigDecimal.valueOf(900000d), 1, true));
        expected.add(new Account(4, "44444444444444444444", BigDecimal.valueOf(99090d), 1, true));

        List<Account> actual = accountDAOImpl.getAllForUser(1);

        assertEquals(expected, actual);
    }

    @Test
    void addMoney() {
        BigDecimal expected1 = accountDAOImpl.getBalance(1).add(BigDecimal.valueOf(333.33));

        accountDAOImpl.addMoney(1, BigDecimal.valueOf(333.33));

        BigDecimal actual1 = accountDAOImpl.getBalance(1);

        assertEquals(expected1, actual1);
    }

    @Test
    void withdrawMoney() {
        BigDecimal expected1 = accountDAOImpl.getBalance(1).subtract(BigDecimal.valueOf(333.33d));

        accountDAOImpl.withdrawMoney(1, BigDecimal.valueOf(333.33d));

        BigDecimal actual1 = accountDAOImpl.getBalance(1);

        assertEquals(expected1, actual1);
    }

    @Test
    void transaction() {
        BigDecimal value = BigDecimal.valueOf(10000d);
        int sendCardId = 1;
        int getCardId = 4;
        BigDecimal expectedSend = accountDAOImpl.getBalance(sendCardId).subtract(value);
        BigDecimal expectedGet = accountDAOImpl.getBalance(getCardId).add(value);

        accountDAOImpl.transaction(sendCardId, getCardId, value);
        BigDecimal actualSend = accountDAOImpl.getBalance(sendCardId);
        BigDecimal actualGet = accountDAOImpl.getBalance(getCardId);

        assertEquals(expectedSend, actualSend);
        assertEquals(expectedGet, actualGet);
    }
}