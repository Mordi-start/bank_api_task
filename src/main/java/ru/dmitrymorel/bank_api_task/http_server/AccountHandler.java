package ru.dmitrymorel.bank_api_task.http_server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ru.dmitrymorel.bank_api_task.model.Account;
import ru.dmitrymorel.bank_api_task.service.AccountService;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

public class AccountHandler implements HttpHandler {

    private final AccountService accountService;
    private final ObjectMapper objectMapper;

    public AccountHandler(AccountService accountService) {
        this.accountService = accountService;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        if ("GET".equals(httpExchange.getRequestMethod())) {
            handleGetRequest(httpExchange);
        } else if ("POST".equals(httpExchange.getRequestMethod())) {
            handlePostRequest(httpExchange);
        }
    }

    private void handlePostRequest(HttpExchange httpExchange) throws IOException {
        Account account = objectMapper.readValue(httpExchange
                .getRequestBody(), Account.class);
        int account_id = account.getId();
        BigDecimal income = account.getBalance();

        accountService.updateBalance(account_id, income);
    }

    private void handleGetRequest(HttpExchange httpExchange) throws IOException {
        int id = Integer.parseInt(httpExchange
                .getRequestURI()
                .toString()
                .split("\\?")[1]
                .split("=")[1]);
        BigDecimal balance = accountService.getBalance(id);
        String result = objectMapper.writeValueAsString(balance);

        httpExchange.sendResponseHeaders(200, result.length());
        OutputStream outputStream = httpExchange.getResponseBody();

        outputStream.write(result.getBytes());
        outputStream.close();
    }
}
