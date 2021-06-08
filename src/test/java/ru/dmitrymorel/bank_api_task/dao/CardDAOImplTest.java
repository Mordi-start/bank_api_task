package ru.dmitrymorel.bank_api_task.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.dmitrymorel.bank_api_task.database.DatabaseConfig;
import ru.dmitrymorel.bank_api_task.model.Card;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CardDAOImplTest {

    private final CardDAOImpl cardDAOImpl = new CardDAOImpl();
    private List<Card> actual = new ArrayList<>();

    @BeforeEach
    void beforeTests() {
        DatabaseConfig.createTables();
    }

    @Test
    void testCheckCardNumber() {
        boolean check = cardDAOImpl.checkCardNumber("1111");

        assertFalse(check);
    }

    @Test
    void checkCardEnabled() {
        boolean enabledFalse = cardDAOImpl.checkCardEnabled(2);
        boolean enabledTrue = cardDAOImpl.checkCardEnabled(1);

        assertFalse(enabledFalse);
        assertTrue(enabledTrue);
    }

    @Test
    void getAllForAccount() {
        List<Card> expected = new ArrayList<>();
        expected.add(new Card(1, "1111", 1, true));
        expected.add(new Card(2, "1111 5555 3333 4444", 1, false));
        expected.add(new Card(3, "1111 2222 5555 4444", 1, true));

        actual = cardDAOImpl.getAllForAccount(1);

        assertEquals(expected, actual);
    }

    @Test
    void getAllForUser() {
        List<Card> expected = new ArrayList<>();
        expected.add(new Card(1, "1111", 1, true));
        expected.add(new Card(2, "1111 5555 3333 4444", 1, false));
        expected.add(new Card(3, "1111 2222 5555 4444", 1, true));
        expected.add(new Card(6, "3333 2222 5555 4444", 4, true));

        actual = cardDAOImpl.getAllForUser(1);

        assertEquals(expected, actual);
    }

    @Test
    void saveForAccount() {
        int cardId = 8;
        Card expected = new Card(cardId, null, 1, false);

        cardDAOImpl.saveForAccount(1);

        List<Card> cardList = cardDAOImpl.getAllForAccount(1);

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