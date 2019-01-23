package improvedhttpcookiesparser;

import improvedhttpcookiesparser.helpers.handlers.CookiesHandler;
import improvedhttpcookiesparser.helpers.handlers.Handler;
import improvedhttpcookiesparser.helpers.reader.HttpReader;
import improvedhttpcookiesparser.helpers.reader.HttpReaderImpl;
import improvedhttpcookiesparser.http.HttpConstants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Application {

    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, HttpConstants.CHARSET))) {
            HttpReader httpReader = new HttpReaderImpl(reader);
            Handler handler = new CookiesHandler(httpReader, System.out::println);
//            Handler handler = new ResponseHandler(httpReader, System.out::println);
            handler.handle();
        } catch (IOException | IllegalArgumentException e) {
            LOGGER.log(Level.SEVERE, "ERROR", e);
        }
    }
}
