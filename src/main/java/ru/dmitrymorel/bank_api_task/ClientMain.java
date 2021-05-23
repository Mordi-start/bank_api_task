package ru.dmitrymorel.bank_api_task;

import ru.dmitrymorel.bank_api_task.database.DatabaseConfig;
import ru.dmitrymorel.bank_api_task.http_server.BaseServer;

import java.io.IOException;

public class ClientMain {
    public static void main(String[] args) {

        DatabaseConfig.createTables();
        BaseServer baseServer = new BaseServer();
        try {
            baseServer.startServer();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        AccountDAO accountDAO = new AccountDAO();
//        accountDAO.getAllForUser(1).forEach(System.out::println);
//        System.out.println("==============");
//        accountDAO.addMoney(1, BigDecimal.valueOf(102021));
//        accountDAO.withdrawMoney(6, BigDecimal.valueOf(111111));
//        accountDAO.getAllForUser(1).forEach(System.out::println);
//        CardDAO cardDAO = new CardDAO();
//
//        cardDAO.saveForAccount(1);
//        cardDAO.saveForAccount(1);
//        cardDAO.saveForAccount(4);
//        cardDAO.saveForAccount(4);
//        cardDAO.saveForAccount(4);
//
//        cardDAO.getAllForAccount(1).forEach(System.out::println);
//        System.out.println("============");
//
//        cardDAO.getAllForAccount(4).forEach(System.out::println);
    }
}
