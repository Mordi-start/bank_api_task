package ru.dmitrymorel.bank_api_task.http_server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ru.dmitrymorel.bank_api_task.dao.CardDAO;
import ru.dmitrymorel.bank_api_task.model.Card;
import ru.dmitrymorel.bank_api_task.service.CardService;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.List;

public class TestHandler implements HttpHandler {

    private final CardService cardService;
    private final ObjectMapper objectMapper;

    public TestHandler (CardService cardService) {
        this.cardService = cardService;
        this.objectMapper = new ObjectMapper();
    }
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        Card card = cardService.get(2);

        String cardString = objectMapper.writeValueAsString(card);

//        objectMapper.writeValue(stringWriter, card);
//
//        String cardString = stringWriter.toString();
//        card.setId(1);
//        card.setNumber("1111 1111 1111 1111");
//        card.setType("DEB");
//        card.setPaymentSystem("VISA");
//        card.setAccountId(1);
//        card = new Card();
//        Card card = new Card();
//        card.setId(1);
//        card.setNumber("1111 1111 1111 1111");
//        card.setType("DEB");
//        card.setPaymentSystem("VISA");
//        card.setAccountId(1);
//        card = new Card();

        httpExchange.sendResponseHeaders(200, cardString.length());
        OutputStream outputStream = httpExchange.getResponseBody();

        outputStream.write(cardString.getBytes());
    }
}
