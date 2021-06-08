package ru.dmitrymorel.bank_api_task.dao.dao_interfaces;

import ru.dmitrymorel.bank_api_task.model.Card;

import java.util.List;

public interface CardDAO {

    void addNewCardByAccount(int accountId);

    List<Card> getAllForUser(int userId);

    List<Card> getAllForAccount(int accountId);

    void changeCardStatus(int cardId);
}
