package ru.dmitrymorel.bank_api_task.service;

import ru.dmitrymorel.bank_api_task.dao.AccountDAO;
import ru.dmitrymorel.bank_api_task.dao.CardDAO;
import ru.dmitrymorel.bank_api_task.model.Card;

import java.sql.SQLException;
import java.util.List;

public class CardService /*implements CrudService<Card>*/ {

    private static final CardDAO cardDAO = new CardDAO();
    private static final AccountDAO accountDAO = new AccountDAO();
//    @Override
//    public Card get(int id) {
//        return cardDAO.get(id);
//    }
//
//    @Override
//    public List<Card> getAll() {
//        return cardDAO.getAll();
//    }

    public List<Card> getAllForAccount(int account_id) {
        return cardDAO.getAllForAccount(account_id);
    }

    public List<Card> getAllForUser(int user_id) {
        return cardDAO.getAllForUser(user_id);
    }

//    @Override
//    public void save(Card card) {
//        cardDAO.save(card);
//    }

    public void saveForAccount(int account_id) {
        if(cardDAO.checkAccountExists(account_id) && accountDAO.checkAccountEnabled(account_id)) {
            cardDAO.saveForAccount(account_id);
        }
        else {
            try {
                throw new SQLException("Счет не существует или неактивен");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

//    @Override
//    public void update(int id, Card card) {
//        cardDAO.update(id, card);
//    }
//
//    @Override
//    public void delete(int id) {
//        cardDAO.delete(id);
//    }


}
