package ru.dmitrymorel.bank_api_task.http_server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ru.dmitrymorel.bank_api_task.model.Card;
import ru.dmitrymorel.bank_api_task.service.CardService;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CardHandler implements HttpHandler {

    private final CardService cardService;
    private final ObjectMapper objectMapper;

    public CardHandler(CardService cardService) {
        this.cardService = cardService;
        this.objectMapper = new ObjectMapper();
    }


    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        if ("GET".equals(httpExchange.getRequestMethod())) {
            handleGetRequest(httpExchange);
        } else if ("POST".equals(httpExchange)) {
            handlePostRequest(httpExchange);
        }

        handleResponse(httpExchange);
    }

    private void handleResponse(HttpExchange httpExchange) throws IOException {
        String success = "Success";
        httpExchange.sendResponseHeaders(200, success.length());
    }

    private void handlePostRequest(HttpExchange httpExchange) throws IOException {
        Card card = objectMapper.readValue(httpExchange.getRequestBody(),
                Card.class);
        String type = card.getType();
        String paymentSystem = card.getPaymentSystem();
        int accountId = card.getAccountId();
        cardService.saveForAccount(type, paymentSystem, accountId);
    }

    private void handleGetRequest(HttpExchange httpExchange) throws IOException {
        List<Card> cardList = cardService.getAll();
        ArrayNode array = objectMapper.valueToTree(cardList);
        JsonNode result = objectMapper.createObjectNode().set("cards", array);

        String stringResult = result.toString();

        httpExchange.sendResponseHeaders(200, stringResult.length());
        OutputStream outputStream = httpExchange.getResponseBody();

        outputStream.write(stringResult.getBytes());
    }
}