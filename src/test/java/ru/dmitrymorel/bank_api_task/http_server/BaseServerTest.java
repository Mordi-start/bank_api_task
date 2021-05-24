package ru.dmitrymorel.bank_api_task.http_server;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.sun.net.httpserver.HttpServer;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import ru.dmitrymorel.bank_api_task.dao.CardDAO;
import ru.dmitrymorel.bank_api_task.database.DatabaseConfig;
import ru.dmitrymorel.bank_api_task.model.Card;
import ru.dmitrymorel.bank_api_task.service.AccountService;
import ru.dmitrymorel.bank_api_task.service.CardService;
import ru.dmitrymorel.bank_api_task.service.UserService;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseServerTest extends TestCase {

    private final ClientHandler clientHandler =
            new ClientHandler(new CardService(),
                    new AccountService());
    private static List<Card> actual = new ArrayList<>();
    private static List<Card> expected = new ArrayList<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void doBefore() throws SQLException {
        DatabaseConfig.createTables();
    }

    @Test
    public void testStartServer() throws IOException {
//        HttpServer server = HttpServer.create(
//                new InetSocketAddress(8000), 0);
//        UserService userService = new UserService();
//        AccountService accountService = new AccountService();
//        CardService cardService = new CardService();
//
//        ClientHandler clientHandler = new ClientHandler(cardService, accountService);
//
//        server.createContext("/client/getAllCardsForUser", clientHandler);
//
//        server.start();
//
//        final URL urlGet = new URL("http://localhost:8000/client/getAllCardsForUser?id=1");
//        final HttpURLConnection conGet = (HttpURLConnection) urlGet.openConnection();
//        conGet.setRequestMethod("GET");
//        conGet.setRequestProperty("Content-Type", "application/json");
//        final StringBuilder content = new StringBuilder();
//
//        CardDAO cardDAO = new CardDAO();
//        actual = cardDAO.getAllForUser(1);
//        ArrayNode array = objectMapper.valueToTree(actual);
//        JsonNode result = objectMapper.createObjectNode().set("cards", array);
//
//        try (final BufferedReader in = new BufferedReader(
//                new InputStreamReader(conGet.getInputStream()))) {
//            String inputLine;
//            while ((inputLine = in.readLine()) != null) {
//                content.append(inputLine);
//            }
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        }
//
//        String expectedGet = result.toString();
//        String actualGet = content.toString();
//
//        assertEquals(expectedGet, actualGet);

        CardDAO cardDAO = new CardDAO();
        HttpServer server = HttpServer.create(
                new InetSocketAddress(8000), 0);
        UserService userService = new UserService();
        AccountService accountService = new AccountService();
        CardService cardService = new CardService();

        ClientHandler clientHandler = new ClientHandler(cardService, accountService);

        server.createContext("/client/createCardForAccount", clientHandler);

        server.start();

        final URL urlPost = new URL("http://localhost:8000/client/createCardForAccount");
        final HttpURLConnection connection = (HttpURLConnection) urlPost.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
//        connection.setRequestProperty("Accept", "application/json");
        final StringBuilder contentPost = new StringBuilder();

        String jsonString = "{\"accountId\": \"1\"}";
        Card expected = new Card(8, null, 1, false);

        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }
//        try (BufferedReader br = new BufferedReader(
//                new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
//            StringBuilder response = new StringBuilder();
//            String responseLine = null;
//            while ((responseLine = br.readLine()) != null) {
//                response.append(responseLine.trim());
//            }
//            System.out.println(response.toString());
//        }

        Card actual = cardDAO.get(8);
        expected.setNumber(actual.getNumber());

        assertEquals(expected, actual);
    }
}