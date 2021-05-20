package ru.dmitrymorel.bank_api_task.http_server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ru.dmitrymorel.bank_api_task.model.AdminRequest;
import ru.dmitrymorel.bank_api_task.model.User;
import ru.dmitrymorel.bank_api_task.service.UserService;

import java.io.IOException;

public class AdminUserHandler implements HttpHandler {

    private final UserService userService;
    private final ObjectMapper objectMapper;

    public AdminUserHandler(UserService userService) {
        this.userService = userService;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        if ("get".equalsIgnoreCase(httpExchange.getRequestMethod())) {
            handleGetRequest(httpExchange);
        }
        if ("post".equalsIgnoreCase(httpExchange.getRequestMethod())) {
            handlePostRequest(httpExchange);
        }
    }

    private void handlePostRequest(HttpExchange httpExchange) throws IOException {
        User user = objectMapper.readValue(httpExchange
        .getRequestBody(), User.class);

        userService.save(user);
    }

    private void handleGetRequest(HttpExchange httpExchange) throws IOException {
        int id = Integer.parseInt(httpExchange
                .getRequestURI()
                .toString()
                .split("\\?")[1]
                .split("=")[1]);

        User user = userService.get(id);

        if (user.isEnabled()) {
            handlePostRequest(httpExchange);
        }
    }
}
