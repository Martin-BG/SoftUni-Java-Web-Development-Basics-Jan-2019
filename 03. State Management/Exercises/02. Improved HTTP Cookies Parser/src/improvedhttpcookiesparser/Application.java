package improvedhttpcookiesparser;

import improvedhttpcookiesparser.http.HttpConstants;
import improvedhttpcookiesparser.utils.RequestHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Application {

    private static final Logger LOGGER = Logger.getLogger(RequestHandler.class.getName());

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, HttpConstants.CHARSET))) {
            RequestHandler requestHandler = new RequestHandler(reader, System.out::println);
            requestHandler.handle();
        } catch (IOException | IllegalArgumentException e) {
            LOGGER.log(Level.SEVERE, "ERROR", e);
        }
    }
}
