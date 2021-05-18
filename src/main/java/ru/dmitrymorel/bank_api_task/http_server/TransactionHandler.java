package ru.dmitrymorel.bank_api_task.http_server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ru.dmitrymorel.bank_api_task.model.TransactionRequest;
import ru.dmitrymorel.bank_api_task.service.AccountService;

import java.io.IOException;
import java.math.BigDecimal;

public class TransactionHandler implements HttpHandler {

    private final AccountService accountService;
    private final ObjectMapper objectMapper;

    public TransactionHandler(AccountService accountService) {
        this.accountService = accountService;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        TransactionRequest transactionRequest = objectMapper.readValue(httpExchange
                .getRequestBody(), TransactionRequest.class );
        int gettingAccountId = transactionRequest.getGettingAccountId();
        int sendAccountId = transactionRequest.getSendAccountId();
        BigDecimal income = transactionRequest.getIncome();

        accountService.transaction(sendAccountId, gettingAccountId, income);
    }
}
