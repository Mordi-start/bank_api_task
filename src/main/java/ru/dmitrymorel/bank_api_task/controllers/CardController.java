package ru.dmitrymorel.bank_api_task.controllers;

import ru.dmitrymorel.bank_api_task.model.Card;
import ru.dmitrymorel.bank_api_task.service.CardService;

import java.util.List;

public class CardController implements CrudController<Card> {

    private static final CardService cardService = new CardService();

    public Card get(int id) {
        return cardService.get(id);
    }

    public List<Card> getAll() {
        return cardService.getAll();
    }

    public List<Card> getAllForAccount(int account_id) {
        return cardService.getAllForAccount(account_id);
    }

    public List<Card> getAllForUser(int user_id) {
        return cardService.getAllForUser(user_id);
    }

    public void save(Card card) {
        cardService.save(card);
    }

    public void saveForAccount(String type, String paymentSystem, int account_id) {
        cardService.saveForAccount(type, paymentSystem, account_id);
    }

    public void update(int id, Card card) {
        cardService.update(id, card);
    }

    public void delete(int id) {
        cardService.delete(id);
    }


}
