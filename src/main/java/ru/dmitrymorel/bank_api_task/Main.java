package ru.dmitrymorel.bank_api_task;

import ru.dmitrymorel.bank_api_task.controllers.AccountController;
import ru.dmitrymorel.bank_api_task.controllers.CardController;
import ru.dmitrymorel.bank_api_task.controllers.UserController;
import ru.dmitrymorel.bank_api_task.model.Card;
import ru.dmitrymorel.bank_api_task.model.User;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        CardController cardController = new CardController();
        AccountController accountController = new AccountController();

        /*List<Card> cardList = cardController.getAll();
        cardList.forEach(System.out::println);
        System.out.println("==============");

        List<Card> cardListForAccount = cardController.getAllForAccount(1);
        cardListForAccount.forEach(System.out::println);
        System.out.println("==============");

        List<Card> cardListForUser = cardController.getAllForUser(1);
        cardListForUser.forEach(System.out::println);
        System.out.println("==============");

        cardController.saveForAccount("DEB", "MIR", 1);
        cardList = cardController.getAll();
        cardList.forEach(System.out::println);
        System.out.println("==============");*/

        System.out.println(accountController.getBalance(1));
        System.out.println("==============");

        accountController.updateBalance(1, BigDecimal.valueOf(-900000000));
        System.out.println(accountController.getBalance(1));
        System.out.println("==============");

//        UserController userController = new UserController();
//
//        User user;
//        System.out.println(user = userController.get(1));
//        System.out.println(userController.get(2));
//        System.out.println(userController.get(3));
//        System.out.println("+=============");
//
//        userController.getAllUsers().forEach(System.out::println);
//        System.out.println("==============");
//
////        userController.delete(1);
////        userController.getAllUsers().forEach(System.out::println);
////        System.out.println("==============");
//
//        userController.save(user);
//        userController.getAllUsers().forEach(System.out::println);
//        System.out.println("==============");
//
//        user.setName("Max");
//        userController.update(1, user);
//        userController.getAllUsers().forEach(System.out::println);
//        System.out.println("==============");
    }
}
