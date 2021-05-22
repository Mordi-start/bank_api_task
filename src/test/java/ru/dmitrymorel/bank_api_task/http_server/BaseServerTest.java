package ru.dmitrymorel.bank_api_task.http_server;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.sun.net.httpserver.HttpServer;
import junit.framework.TestCase;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import ru.dmitrymorel.bank_api_task.dao.CardDAO;
import ru.dmitrymorel.bank_api_task.model.Card;
import ru.dmitrymorel.bank_api_task.service.AccountService;
import ru.dmitrymorel.bank_api_task.service.CardService;
import ru.dmitrymorel.bank_api_task.service.UserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BaseServerTest extends TestCase {

    private final BaseServer baseServer = new BaseServer();
    private final ClientHandler clientHandler =
            new ClientHandler(new CardService(),
                    new AccountService());
    private static List<Card> actual = new ArrayList<>();
    private static List<Card> expected = new ArrayList<>();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private Connection connection;

    @Before
    public void doBefore() throws SQLException {
        actual.clear();
        expected.clear();
        final String URL = "jdbc:h2:/Users/a19188814/Desktop/bank_api_task/src/main/resources/MyBankDB";
        final String USER = "user";
        final String PASSWORD = "password";

        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USER,
                    PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT * FROM cards " +
                        "JOIN ACCOUNTS " +
                        "ON CARDS.ACCOUNT_ID = ACCOUNTS.ID " +
                        "JOIN USERS " +
                        "ON ACCOUNTS.USER_ID = USERS.ID " +
                        "WHERE USER_ID = ?");

        preparedStatement.setInt(1, 1);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Card card = new Card();

            card.setId(resultSet.getInt("id"));
            card.setNumber(resultSet.getString("number"));
            card.setAccountId(resultSet.getInt("account_id"));
            card.setEnabled(resultSet.getBoolean("enabled"));

            expected.add(card);
        }
    }

    @SneakyThrows
    @Test
    public void testStartServer() {
        HttpServer server = HttpServer.create(
                new InetSocketAddress(8080), 0);
        UserService userService = new UserService();
        AccountService accountService = new AccountService();
        CardService cardService = new CardService();

        ClientHandler clientHandler = new ClientHandler(cardService, accountService);

        server.createContext("/client/getAllAccounts", clientHandler);
        server.createContext("/client/getAllCardsForAccount", clientHandler);
        server.createContext("/client/getAllCardsForUser", clientHandler);
        server.createContext("/client/createCardForAccount", clientHandler);
        server.createContext("/client/getBalanceForAccount", clientHandler);
        server.createContext("/client/updateBalance", clientHandler);
        server.createContext("/client/doTransaction", clientHandler);

        server.start();

        final URL url = new URL("http://localhost:8080/client/getAllCardsForUser?id=1");
        final HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        final StringBuilder content = new StringBuilder();

        CardDAO cardDAO = new CardDAO();
        actual = cardDAO.getAllForUser(1);
        ArrayNode array = objectMapper.valueToTree(actual);
        JsonNode result = objectMapper.createObjectNode().set("cards", array);

        baseServer.startServer();

        try (final BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        String expected = result.toString();
        String actual = content.toString();

        assertEquals(expected, actual);
    }
}