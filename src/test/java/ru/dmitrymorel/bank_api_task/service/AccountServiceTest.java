package ru.dmitrymorel.bank_api_task.service;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import ru.dmitrymorel.bank_api_task.database.DatabaseConfig;

import java.math.BigDecimal;

public class AccountServiceTest extends TestCase {

    private final AccountService accountService = new AccountService();

    @Before
    public void beforeTests() {
        DatabaseConfig.createTables();
    }

    @Test
    public void testAddMoney() {
        BigDecimal expected1 = accountService.getBalance(1).add(BigDecimal.valueOf(9999d));
        BigDecimal expected2 = accountService.getBalance(7);

        accountService.addMoney(1, BigDecimal.valueOf(9999d));
        accountService.addMoney(7, BigDecimal.valueOf(9999d));

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
        BigDecimal expected2 = accountService.getBalance(4).subtract(BigDecimal.valueOf(333333333.33d));

        accountService.withdrawMoney(1, BigDecimal.valueOf(333.33d));
        accountService.withdrawMoney(4, BigDecimal.valueOf(333333333.33d));

        BigDecimal actual1 = accountService.getBalance(1);
        BigDecimal actual2 = accountService.getBalance(1);

        assertEquals(expected1, actual1);
        assertNotSame(expected2, actual2);
    }
}