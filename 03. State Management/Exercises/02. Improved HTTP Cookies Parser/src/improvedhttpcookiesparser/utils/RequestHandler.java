package improvedhttpcookiesparser.utils;

import improvedhttpcookiesparser.http.HttpConstants;
import improvedhttpcookiesparser.http.HttpRequest;
import improvedhttpcookiesparser.http.HttpRequestImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.function.Consumer;

public class RequestHandler {

    private Consumer<String> resultConsumer;
    private BufferedReader reader;

    public RequestHandler(BufferedReader reader, Consumer<String> resultConsumer) {
        this.reader = reader;
        this.resultConsumer = resultConsumer;
    }

    private static String parseInput(BufferedReader reader) throws IOException {
        StringBuilder request = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            request.append(line).append(HttpConstants.HTTP_LINE_SEPARATOR);
            if (line.isEmpty()) {
                break;
            }
        }
        request.append(reader.readLine());
        return request.toString();
    }

    public void handle() throws IOException {
        String request = parseInput(reader);
        HttpRequest httpRequest = new HttpRequestImpl(request);
        StringBuilder response = new StringBuilder();
        httpRequest.getCookies().forEach(cookie -> response
                .append(cookie.getKey())
                .append(" <-> ")
                .append(cookie.getValue())
                .append(System.lineSeparator()));
        resultConsumer.accept(response.toString());
    }
}
