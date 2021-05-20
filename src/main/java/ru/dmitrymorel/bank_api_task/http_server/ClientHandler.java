package ru.dmitrymorel.bank_api_task.http_server;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ru.dmitrymorel.bank_api_task.model.Account;
import ru.dmitrymorel.bank_api_task.model.BalanceRequest;
import ru.dmitrymorel.bank_api_task.model.Card;
import ru.dmitrymorel.bank_api_task.model.TransactionRequest;
import ru.dmitrymorel.bank_api_task.service.AccountService;
import ru.dmitrymorel.bank_api_task.service.CardService;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.List;

public class ClientHandler implements HttpHandler {

    private final CardService cardService;
    private final ObjectMapper objectMapper;
    private final AccountService accountService;

    public ClientHandler(CardService cardService, AccountService accountService) {
        this.cardService = cardService;
        this.accountService = accountService;
        this.objectMapper = new ObjectMapper();
    }


    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String context = httpExchange.getHttpContext().getPath();
        switch (context) {
            case "/client/getAllAccounts": {
                handleGetAllAccountsForUser(httpExchange);
            }
            case "/client/getAllCardsForAccount": {
                handleGetAllCardsForAccount(httpExchange);
            }
            case "/client/getAllCardsForUser": {
                handleGetAllCardsForUser(httpExchange);
            }
            case "/client/createCardForAccount": {
                handlePostCreateCardForAccount(httpExchange);
            }
            case "/client/getBalanceForAccount": {
                handleGetRequestForBalance(httpExchange);
            }
            case "/client/addMoney": {
                handlePostAddMoney(httpExchange);
            }
            case "/client/doTransaction": {
                handlePostTransaction(httpExchange);
            }
            default: {

            }
        }
//        if ("GET".equals(httpExchange.getRequestMethod())) {
//            handleGetRequest(httpExchange);
//        } else if ("POST".equals(httpExchange.getRequestMethod())) {
//            handlePostRequest(httpExchange);
//        }
    }

    private void handlePostTransaction(HttpExchange httpExchange) throws IOException {
        TransactionRequest transactionRequest = objectMapper.readValue(httpExchange
                .getRequestBody(), TransactionRequest.class);
        int sendAccountId = transactionRequest.getSendAccountId();
        int getAccountId = transactionRequest.getGettingAccountId();
        BigDecimal income = transactionRequest.getValue();

        accountService.transaction(sendAccountId, getAccountId, income);
    }

    private void handleGetAllAccountsForUser(HttpExchange httpExchange) throws IOException {
        int id = Integer.parseInt(httpExchange
                .getRequestURI()
                .toString()
                .split("\\?")[1]
                .split("=")[1]);
        List<Account> cardList = accountService.getAllForUser(id);
        ArrayNode array = objectMapper.valueToTree(cardList);
        JsonNode result = objectMapper.createObjectNode().set("accounts", array);

        String stringResult = result.toString();

        httpExchange.sendResponseHeaders(200, stringResult.length());
        OutputStream outputStream = httpExchange.getResponseBody();

        outputStream.write(stringResult.getBytes());
        outputStream.close();
    }

    private void handlePostAddMoney(HttpExchange httpExchange) throws IOException {
        Account account = objectMapper.readValue(httpExchange
                .getRequestBody(), Account.class);
        int account_id = account.getId();
        BigDecimal income = account.getBalance();

        accountService.addMoney(account_id, income);
    }

    private void handleGetRequestForBalance(HttpExchange httpExchange) throws IOException {
        int id = Integer.parseInt(httpExchange
                .getRequestURI()
                .toString()
                .split("\\?")[1]
                .split("=")[1]);
        BigDecimal balance = accountService.getBalance(id);
        BalanceRequest balanceRequest = new BalanceRequest(id, balance);
        String result = objectMapper.writeValueAsString(balanceRequest);

        httpExchange.sendResponseHeaders(200, result.length());
        OutputStream outputStream = httpExchange.getResponseBody();

        outputStream.write(result.getBytes());
        outputStream.close();
    }

    private void handleGetAllCardsForUser(HttpExchange httpExchange) throws IOException {
        int id = Integer.parseInt(httpExchange
                .getRequestURI()
                .toString()
                .split("\\?")[1]
                .split("=")[1]);
        List<Card> cardList = cardService.getAllForUser(id);
        ArrayNode array = objectMapper.valueToTree(cardList);
        JsonNode result = objectMapper.createObjectNode().set("cards", array);

        String stringResult = result.toString();

        httpExchange.sendResponseHeaders(200, stringResult.length());
        OutputStream outputStream = httpExchange.getResponseBody();

        outputStream.write(stringResult.getBytes());
        outputStream.close();
    }

    private void handlePostCreateCardForAccount(HttpExchange httpExchange) throws IOException {
        Card card = objectMapper.readValue(httpExchange.getRequestBody(),
                Card.class);
        int accountId = card.getAccountId();
        cardService.saveForAccount(accountId);
    }

    private void handleGetAllCardsForAccount(HttpExchange httpExchange) throws IOException {
        int id = Integer.parseInt(httpExchange
                .getRequestURI()
                .toString()
                .split("\\?")[1]
                .split("=")[1]);
        List<Card> cardList = cardService.getAllForAccount(id);
        ArrayNode array = objectMapper.valueToTree(cardList);
        JsonNode result = objectMapper.createObjectNode().set("cards", array);

        String stringResult = result.toString();

        httpExchange.sendResponseHeaders(200, stringResult.length());
        OutputStream outputStream = httpExchange.getResponseBody();

        outputStream.write(stringResult.getBytes());
        outputStream.close();
    }
}
