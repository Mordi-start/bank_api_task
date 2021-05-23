package ru.dmitrymorel.bank_api_task.dao;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import ru.dmitrymorel.bank_api_task.database.DatabaseConfig;
import ru.dmitrymorel.bank_api_task.model.Card;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class CardDAOTest extends TestCase {

    private final Connection connection = DatabaseConfig.getConnection();
    private final CardDAO cardDAO = new CardDAO();
    private List<Card> actual = new ArrayList<>();

    @Before
    public void beforeTests() {
        DatabaseConfig.createTables();
    }

    @Test
    public void testCheckCardNumber() {
        boolean check = cardDAO.checkCardNumber("1111");

        assertFalse(check);
    }

    @Test
    public void testCheckCardEnabled() {
        boolean enabledFalse = cardDAO.checkCardEnabled(2);
        boolean enabledTrue = cardDAO.checkCardEnabled(1);

        assertFalse(enabledFalse);
        assertTrue(enabledTrue);
    }

    @Test
    public void testGetAllForAccount() {
        List<Card> expected = new ArrayList<>();
        expected.add(new Card(1, "1111", 1, true));
        expected.add(new Card(2, "1111 5555 3333 4444", 1, false));
        expected.add(new Card(3, "1111 2222 5555 4444", 1, true));

        actual = cardDAO.getAllForAccount(1);

        assertEquals(expected, actual);
    }

    @Test
    public void testGetAllForUser() {
        List<Card> expected = new ArrayList<>();
        expected.add(new Card(1, "1111", 1, true));
        expected.add(new Card(2, "1111 5555 3333 4444", 1, false));
        expected.add(new Card(3, "1111 2222 5555 4444", 1, true));
        expected.add(new Card(6, "3333 2222 5555 4444", 4, true));

        actual = cardDAO.getAllForUser(1);

        assertEquals(expected, actual);
    }

    @Test
    public void testSaveForAccount() {
        int cardId = 8;
        Card expected = new Card(cardId, null, 1, false);

        cardDAO.saveForAccount(1);

        List<Card> cardList = cardDAO.getAllForAccount(1);

        Card actualCard = null;

        for (Card card : cardList) {
            if (card.getId() == cardId) {
                actualCard = card;
            }
        }

        expected.setNumber(actualCard.getNumber());

        assertEquals(expected, actualCard);
    }
}