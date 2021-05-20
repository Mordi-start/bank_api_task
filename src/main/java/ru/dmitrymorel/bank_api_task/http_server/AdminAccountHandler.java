package ru.dmitrymorel.bank_api_task.http_server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ru.dmitrymorel.bank_api_task.service.AccountService;

import java.io.IOException;

public class AdminAccountHandler implements HttpHandler {
    public AdminAccountHandler(AccountService accountService) {
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

    }
}
