package ru.dmitrymorel.bank_api_task;

import ru.dmitrymorel.bank_api_task.controllers.AccountController;
import ru.dmitrymorel.bank_api_task.controllers.CardController;
import ru.dmitrymorel.bank_api_task.controllers.UserController;
import ru.dmitrymorel.bank_api_task.dao.AccountDAO;
import ru.dmitrymorel.bank_api_task.http_server.BaseServer;
import ru.dmitrymorel.bank_api_task.model.Card;
import ru.dmitrymorel.bank_api_task.model.User;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class ClientMain {
    public static void main(String[] args) {
        BaseServer baseServer = new BaseServer();
        try {
            baseServer.startServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
