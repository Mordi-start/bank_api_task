package ru.dmitrymorel.bank_api_task.http_server;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.sun.net.httpserver.HttpServer;
import junit.framework.TestCase;
import org.h2.tools.RunScript;
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
import java.security.cert.CertPathValidatorException;
import java.sql.*;
import java.util.*;

public class BaseServerTest extends TestCase {

    private final static String TEST_PROPERTIES_PATH = "/Users/a19188814/Desktop/bank_api_task/src/test/java/ru/dmitrymorel/bank_api_task/http_server/db.properties";
    private final static String SQL_TEST_SCRIPT_PATH = "/Users/a19188814/Desktop/bank_api_task/src/test/java/ru/dmitrymorel/bank_api_task/http_server/Script.sql";
    private static Connection connection = null;

    public void createTables() {
        try (InputStream inputStream = new FileInputStream(TEST_PROPERTIES_PATH)) {
            Properties properties = new Properties();
            properties.load(inputStream);

            String dbUrl = properties.getProperty("db.url");
            String dbUsername = properties.getProperty("db.username");
            String dbPassword = properties.getProperty("db.password");
            String dbDriverClassName = properties.getProperty("db.driverClassName");

            Class.forName(dbDriverClassName);
            connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

        } catch (IOException | SQLException | ClassNotFoundException e) {
            throw new IllegalStateException();
        }

        try {
            RunScript.execute(connection, new FileReader(SQL_TEST_SCRIPT_PATH));
        } catch (FileNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    public void doBefore() throws SQLException {
        createTables();
    }

    @Test
    public void testStartServer() throws IOException {
        List<Card> actual = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();


        HttpServer server = HttpServer.create(
                new InetSocketAddress(8000), 0);
        AccountService accountService = new AccountService();
        CardService cardService = new CardService();

        ClientHandler clientHandler = new ClientHandler(cardService, accountService);

        server.createContext("/client/getAllCardsForUser", clientHandler);

        server.start();

        final URL urlGet = new URL("http://localhost:8000/client/getAllCardsForUser?id=1");
        final HttpURLConnection conGet = (HttpURLConnection) urlGet.openConnection();
        conGet.setRequestMethod("GET");
        conGet.setRequestProperty("Content-Type", "application/json");
        final StringBuilder content = new StringBuilder();
//
        CardDAO cardDAO = new CardDAO();
        actual = cardDAO.getAllForUser(1);
        ArrayNode array = objectMapper.valueToTree(actual);
        JsonNode result = objectMapper.createObjectNode().set("cards", array);

        try (final BufferedReader in = new BufferedReader(
                new InputStreamReader(conGet.getInputStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        String expectedGet = result.toString();
        String actualGet = content.toString();

        assertEquals(expectedGet, actualGet);

        server.createContext("/client/createCardForAccount", clientHandler);


        final URL urlPost = new URL("http://localhost:8000/client/createCardForAccount");
        final HttpURLConnection connection = (HttpURLConnection) urlPost.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        final StringBuilder contentPost = new StringBuilder();

        String jsonString = "{\"accountId\": \"1\"}";
        Card expected = new Card(8, null, 1, false);

        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
        }

        Card actual2 = cardDAO.get(8);
        expected.setNumber(actual2.getNumber());

        assertEquals(expected, actual2);
    }
}