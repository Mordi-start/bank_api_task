package ru.dmitrymorel.bank_api_task.service;

import ru.dmitrymorel.bank_api_task.dao.AccountDAOImpl;
import ru.dmitrymorel.bank_api_task.dao.CardDAOImpl;
import ru.dmitrymorel.bank_api_task.model.Card;

import java.sql.SQLException;
import java.util.List;

public class CardService /*implements CrudService<Card>*/ {

    private static final CardDAOImpl CARD_DAO_IMPL = new CardDAOImpl();
    private static final AccountDAOImpl ACCOUNT_DAO_IMPL = new AccountDAOImpl();
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
        return CARD_DAO_IMPL.getAllForAccount(account_id);
    }

    public List<Card> getAllForUser(int user_id) {
        return CARD_DAO_IMPL.getAllForUser(user_id);
    }

//    @Override
//    public void save(Card card) {
//        cardDAO.save(card);
//    }

    public void saveForAccount(int account_id) {
        if(ACCOUNT_DAO_IMPL.checkAccountExists(account_id)) {
            if (ACCOUNT_DAO_IMPL.checkAccountEnabled(account_id)) {
                CARD_DAO_IMPL.saveForAccount(account_id);
            }
            else try {
                throw new SQLException();
            } catch (SQLException throwables) {
                System.out.println("Счет неактивен");
            }
        }
        else {
            try {
                throw new SQLException();
            } catch (SQLException throwables) {
                System.out.println("Счет не существует");
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
