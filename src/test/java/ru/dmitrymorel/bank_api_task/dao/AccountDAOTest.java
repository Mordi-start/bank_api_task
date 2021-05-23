package ru.dmitrymorel.bank_api_task.dao;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import ru.dmitrymorel.bank_api_task.database.DatabaseConfig;
import ru.dmitrymorel.bank_api_task.model.Account;
import ru.dmitrymorel.bank_api_task.model.Card;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class AccountDAOTest extends TestCase {

//    private final Connection connection = DatabaseConfig.getConnection();
    private final AccountDAO accountDAO = new AccountDAO();

    @Before
    public void beforeTests() {
        DatabaseConfig.createTables();
    }

    @Test
    public void testCheckAccountExists() {
        boolean check = accountDAO.checkAccountExists(10);
        assertFalse(check);
    }

    @Test
    public void testCheckAccountEnabled() {
        boolean enabledFalse = accountDAO.checkAccountEnabled(5);
        boolean enabledTrue = accountDAO.checkAccountEnabled(1);

        assertFalse(enabledFalse);
        assertTrue(enabledTrue);
    }

    @Test
    public void testGetBalance() {
        BigDecimal expected = BigDecimal.valueOf(900000d);

        BigDecimal actual = accountDAO.getBalance(1);

        assertEquals(expected, actual);
    }

    @Test
    public void testGetAllForUser() {
        List<Account> expected = new ArrayList<>();
        expected.add(new Account(1, "11111111111111111111", BigDecimal.valueOf(900000d), 1, true));
        expected.add(new Account(4, "44444444444444444444", BigDecimal.valueOf(99090d), 1, true));

        List<Account> actual = accountDAO.getAllForUser(1);

        assertEquals(expected, actual);
    }

    @Test
    public void testAddMoney() {
        BigDecimal expected1 = accountDAO.getBalance(1).add(BigDecimal.valueOf(333.33));

        accountDAO.addMoney(1, BigDecimal.valueOf(333.33));

        BigDecimal actual1 = accountDAO.getBalance(1);

        assertEquals(expected1, actual1);
    }

    @Test
    public void testWithdrawMoney() {
        BigDecimal expected1 = accountDAO.getBalance(1).subtract(BigDecimal.valueOf(333.33d));

        accountDAO.withdrawMoney(1, BigDecimal.valueOf(333.33d));

        BigDecimal actual1 = accountDAO.getBalance(1);

        assertEquals(expected1, actual1);
    }

    @Test
    public void testTransaction() {
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