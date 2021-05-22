package ru.dmitrymorel.bank_api_task.http_server;

import com.sun.net.httpserver.HttpServer;
import ru.dmitrymorel.bank_api_task.service.AccountService;
import ru.dmitrymorel.bank_api_task.service.CardService;
import ru.dmitrymorel.bank_api_task.service.UserService;

import java.io.IOException;
import java.net.InetSocketAddress;

public class BaseServer {

    public void startServer() throws IOException {
        HttpServer server = HttpServer.create(
                new InetSocketAddress(8080), 0);
        UserService userService = new UserService();
        AccountService accountService = new AccountService();
        CardService cardService = new CardService();


        ClientHandler clientHandler = new ClientHandler(cardService, accountService);

        server.createContext("/client/getAllAccounts", clientHandler);
        server.createContext("/client/getAllCardsForAccount", clientHandler);
        server.createContext("/client/getAllCardsForUser", clientHandler);
        server.createContext("/client/createCardForAccount", clientHandler);
        server.createContext("/client/getBalanceForAccount", clientHandler);
        server.createContext("/client/addMoney", clientHandler);
        server.createContext("/client/withdrawMoney", clientHandler);
        server.createContext("/client/doTransaction", clientHandler);

//        AdminUserHandler adminUserHandler = new AdminUserHandler(userService);
//        AdminAccountHandler adminAccountHandler = new AdminAccountHandler(accountService);
//        AdminCardHandler adminCardHandler = new AdminCardHandler(accountService);
//        AdminTransactionHandler adminTransactionHandler = new AdminTransactionHandler(accountService);
//
//        HttpContext context = server.createContext("/admin/addNewUser", adminUserHandler);
//        context.setAuthenticator(new BasicAuthenticator("admin") {
//            @Override
//            public boolean checkCredentials(String name, String surname) {
//                return name.equals("Dmitry") && surname.equals("Morel");
//            }
//        });
//        server.createContext("/admin/addNewAccount", adminAccountHandler);
//        server.createContext("/admin/enableCard", adminCardHandler);
//        server.createContext("/admin/confirmOperation", adminTransactionHandler);
        server.start();
    }
}
