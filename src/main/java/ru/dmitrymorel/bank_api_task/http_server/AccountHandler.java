package ru.dmitrymorel.bank_api_task.http_server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ru.dmitrymorel.bank_api_task.service.AccountService;

import java.io.IOException;
import java.math.BigDecimal;

public class AccountHandler implements HttpHandler {

    private final AccountService accountService;
    private final ObjectMapper objectMapper;

    public AccountHandler(AccountService accountService, ObjectMapper objectMapper) {
        this.accountService = accountService;
        this.objectMapper = objectMapper;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        if ("GET".equals(httpExchange.getRequestMethod())) {
            handleGetRequest(httpExchange);
        } else if ("PUT".equals(httpExchange.getRequestMethod())) {
            handlePutRequest(httpExchange);
        }
    }

    private void handlePutRequest(HttpExchange httpExchange) {

    }

    private void handleGetRequest(HttpExchange httpExchange) {
//        httpExchange.
//        BigDecimal balance = accountService.getBalance();
    }
}
