package ru.dmitrymorel.bank_api_task.http_server;

import com.sun.net.httpserver.HttpServer;
import ru.dmitrymorel.bank_api_task.dao.AccountDAO;
import ru.dmitrymorel.bank_api_task.dao.CardDAO;
import ru.dmitrymorel.bank_api_task.dao.UserDAO;
import ru.dmitrymorel.bank_api_task.service.AccountService;
import ru.dmitrymorel.bank_api_task.service.CardService;
import ru.dmitrymorel.bank_api_task.service.UserService;

import java.io.IOException;
import java.net.InetSocketAddress;

public class BaseServer {

    public void startServer() throws IOException {
        HttpServer server = HttpServer.create(
                new InetSocketAddress(8080), 0);
        UserDAO userDAO = new UserDAO();
        AccountDAO accountDAO = new AccountDAO();
        CardDAO cardDAO = new CardDAO();
        UserService userService = new UserService();
        AccountService accountService = new AccountService();
        CardService cardService = new CardService();

        server.createContext("/getAllCardsForAccount", new CardHandler(cardService));
        server.createContext("/test", new TestHandler(cardService));
        server.start();
    }
}