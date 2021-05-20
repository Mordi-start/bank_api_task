package ru.dmitrymorel.bank_api_task.http_server;

import com.sun.net.httpserver.BasicAuthenticator;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpPrincipal;


public class ClientAuth extends BasicAuthenticator {


    public ClientAuth(String realm) {
        super(realm);
    }

    public Result authenticate(HttpExchange exchange) {
        Result result = super.authenticate(exchange);
        if (result instanceof Success) {
            HttpPrincipal principal = ((Success) result).getPrincipal();
            String requestMethod = exchange.getRequestMethod();
            if (requestMethod.equalsIgnoreCase("get")) {

            }
            else if (requestMethod.equalsIgnoreCase("post")) {

            }
        }
        return result;
    }

    @Override
    public boolean checkCredentials(String login, String password) {
//        int authCode = ;
        return false;
    }
}
