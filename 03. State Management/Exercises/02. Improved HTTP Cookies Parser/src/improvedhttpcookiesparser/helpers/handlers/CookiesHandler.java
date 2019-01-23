package improvedhttpcookiesparser.helpers.handlers;

import improvedhttpcookiesparser.helpers.reader.HttpReader;
import improvedhttpcookiesparser.http.HttpRequest;
import improvedhttpcookiesparser.http.HttpRequestImpl;

import java.io.IOException;
import java.util.function.Consumer;

public class CookiesHandler implements Handler {

    private Consumer<String> resultConsumer;
    private HttpReader httpReader;

    public CookiesHandler(HttpReader httpReader, Consumer<String> resultConsumer) {
        this.httpReader = httpReader;
        this.resultConsumer = resultConsumer;
    }

    @Override
    public void handle() throws IOException {
        String request = httpReader.readHttpRequest();
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
