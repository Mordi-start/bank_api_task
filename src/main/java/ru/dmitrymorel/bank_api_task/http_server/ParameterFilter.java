package ru.dmitrymorel.bank_api_task.http_server;

import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParameterFilter extends Filter {
    @Override
    public void doFilter(HttpExchange httpExchange, Chain chain) throws IOException {
        parseGetParameters(httpExchange);
        parsePostParameters(httpExchange);
        chain.doFilter(httpExchange);
    }

    private void parsePostParameters(HttpExchange httpExchange) throws IOException {
        if ("post".equalsIgnoreCase(httpExchange.getRequestMethod())) {
            @SuppressWarnings("unchecked")
                    Map<String, Object> parameters =
                    (Map<String, Object>) httpExchange.getAttribute("parameters");
            InputStreamReader inputStreamReader =
                    new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String query = bufferedReader.readLine();
            parseQuery(query, parameters);
        }
    }

    private void parseGetParameters(HttpExchange httpExchange) throws UnsupportedEncodingException {
        Map<String, Object> parameters = new HashMap<>();
        URI requestedUri = httpExchange.getRequestURI();
        String query = requestedUri.getRawQuery();
        parseQuery(query, parameters);
        httpExchange.setAttribute("parameters", parameters);
    }

    @SuppressWarnings("unchecked")
    private void parseQuery(String query, Map<String, Object> parameters) throws UnsupportedEncodingException {
        if (query != null) {
            String pairs[] = query.split("[&]");

            for (String pair : pairs) {
                String param[] = pair.split("[=]");

                String key = null;
                String value = null;
                if (param.length > 0) {
                    key = URLDecoder.decode(param[0],
                            System.getProperty("file.encoding"));
                }

                if (param.length > 1) {
                    value = URLDecoder.decode(param[1],
                            System.getProperty("file.encoding"));
                }

                if (parameters.containsKey(key)) {
                    Object object = parameters.get(key);
                    if (object instanceof List<?>) {
                        List<String> values = (List<String>)object;
                        values.add(value);
                    } else if (object instanceof String) {
                        List<String> values = new ArrayList<>();
                        values.add((String) object);
                        values.add(value);
                        parameters.put(key, values);
                    }
                } else {
                    parameters.put(key, value);
                }
            }
        }
    }

    @Override
    public String description() {
        return "Parses the requested URI for parameters";
    }
}
