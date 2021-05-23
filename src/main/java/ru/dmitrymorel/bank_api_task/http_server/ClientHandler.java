package ru.dmitrymorel.bank_api_task.http_server;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ru.dmitrymorel.bank_api_task.model.*;
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
                break;
            }
            case "/client/getAllCardsForAccount": {
                handleGetAllCardsForAccount(httpExchange);
                break;
            }
            case "/client/getAllCardsForUser": {
                handleGetAllCardsForUser(httpExchange);
                break;
            }
            case "/client/createCardForAccount": {
                handlePostCreateCardForAccount(httpExchange);
                break;
            }
            case "/client/getBalanceForAccount": {
                handleGetRequestForBalance(httpExchange);
                break;
            }
            case "/client/addMoney": {
                handlePostAddMoney(httpExchange);
                break;
            }
            case "/client/withdrawMoney": {
                handlePostWithdrawMoney(httpExchange);
                break;
            }
            case "/client/doTransaction": {
                handlePostTransaction(httpExchange);
                break;
            }
            default: {

            }
        }
    }

    private void handlePostWithdrawMoney(HttpExchange httpExchange) throws IOException {
        DepositAndWithdrawRequest request = objectMapper.readValue(httpExchange
                .getRequestBody(), DepositAndWithdrawRequest.class);
        int cardId = request.getCardId();
        BigDecimal value = request.getValue();

        accountService.withdrawMoney(cardId, value);
    }

    private void handlePostTransaction(HttpExchange httpExchange) throws IOException {
        TransactionRequest transactionRequest = objectMapper.readValue(httpExchange
                .getRequestBody(), TransactionRequest.class);
        int sendCardId = transactionRequest.getSendCardId();
        int gettingCardId = transactionRequest.getGettingCardId();
        BigDecimal value = transactionRequest.getValue();

        accountService.transaction(sendCardId, gettingCardId, value);
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
        DepositAndWithdrawRequest request = objectMapper.readValue(httpExchange
                .getRequestBody(), DepositAndWithdrawRequest.class);
        int cardId = request.getCardId();
        BigDecimal value = request.getValue();

        accountService.addMoney(cardId, value);
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
