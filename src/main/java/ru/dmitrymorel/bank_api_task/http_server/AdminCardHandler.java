package ru.dmitrymorel.bank_api_task.http_server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ru.dmitrymorel.bank_api_task.service.AccountService;

import java.io.IOException;

public class AdminCardHandler implements HttpHandler {
    public AdminCardHandler(AccountService accountService) {
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

    }
}
