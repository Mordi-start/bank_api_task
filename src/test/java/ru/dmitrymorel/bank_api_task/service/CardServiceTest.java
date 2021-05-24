package ru.dmitrymorel.bank_api_task.service;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import ru.dmitrymorel.bank_api_task.database.DatabaseConfig;
import ru.dmitrymorel.bank_api_task.model.Card;

import java.util.List;

public class CardServiceTest extends TestCase {

    private final CardService cardService = new CardService();

    @BeforeEach
    public void beforeTests() {
        DatabaseConfig.createTables();
    }

    @Test
    public void testSaveForAccount() {
        List<Card> expected = cardService.getAllForAccount(5);

        cardService.saveForAccount(5);

        List<Card> actualCard = cardService.getAllForAccount(5);

        assertEquals(expected, actualCard);
    }
}