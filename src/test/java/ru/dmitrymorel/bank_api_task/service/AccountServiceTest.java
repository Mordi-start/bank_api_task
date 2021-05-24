package ru.dmitrymorel.bank_api_task.service;

import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import ru.dmitrymorel.bank_api_task.database.DatabaseConfig;

import java.math.BigDecimal;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final AccountService accountService = new AccountService();

    @BeforeEach
    public void beforeTests() {
        DatabaseConfig.createTables();
    }

    @Test
    public void testAddMoney() {
        BigDecimal expected1 = accountService.getBalance(1).add(BigDecimal.valueOf(9999d));
        BigDecimal expected2 = accountService.getBalance(7);

        accountService.addMoney(1, BigDecimal.valueOf(9999d));

        thrown.expect(SQLException.class);
        thrown.expectMessage("Карта неактивна");
        accountService.addMoney(7, BigDecimal.valueOf(9999d));
        thrown = ExpectedException.none();

        BigDecimal actual1 = accountService.getBalance(1);
        BigDecimal actual2 = accountService.getBalance(7);

        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
    }

    @Test
    public void testTransaction() {
        BigDecimal value = BigDecimal.valueOf(10000d);
        int sendCardId = 1;
        int getCardId = 4;
        BigDecimal expectedSend = accountService.getBalance(sendCardId).subtract(value);
        BigDecimal expectedGet = accountService.getBalance(getCardId).add(value);

        accountService.transaction(sendCardId, getCardId, value);
        BigDecimal actualSend = accountService.getBalance(sendCardId);
        BigDecimal actualGet = accountService.getBalance(getCardId);

        assertEquals(expectedSend, actualSend);
        assertEquals(expectedGet, actualGet);
    }

    @Test
    public void testWithdrawMoney() {
        BigDecimal expected1 = accountService.getBalance(1).subtract(BigDecimal.valueOf(333.33d));

        accountService.withdrawMoney(1, BigDecimal.valueOf(333.33d));

        thrown.expect(SQLException.class);
        thrown.expectMessage("Карта неактивна");
        accountService.withdrawMoney(7, BigDecimal.valueOf(333333333.33d)); // exception
        thrown = ExpectedException.none();

        BigDecimal actual1 = accountService.getBalance(1);

        assertEquals(expected1, actual1);
    }
}