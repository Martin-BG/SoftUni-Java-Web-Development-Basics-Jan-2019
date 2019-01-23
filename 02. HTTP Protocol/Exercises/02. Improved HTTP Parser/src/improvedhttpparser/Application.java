package improvedhttpparser;

import improvedhttpparser.helpers.handlers.Handler;
import improvedhttpparser.helpers.handlers.ResponseHandler;
import improvedhttpparser.helpers.reader.HttpReader;
import improvedhttpparser.helpers.reader.HttpReaderImpl;
import improvedhttpparser.http.HttpConstants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Application {

    private static final Logger LOGGER = Logger.getLogger(ResponseHandler.class.getName());

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, HttpConstants.CHARSET))) {
            HttpReader httpReader = new HttpReaderImpl(reader);
            Handler handler = new ResponseHandler(httpReader, System.out::println);
            handler.handle();
        } catch (IOException | IllegalArgumentException e) {
            LOGGER.log(Level.SEVERE, "ERROR", e);
        }
    }
}
