package chushka.util;

import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class HtmlReader {

    private static final String HTML_LINE_SEPARATOR = "\r\n";
    private static final Charset HTML_CHARSET = StandardCharsets.UTF_8;

    private final Logger logger;

    @Inject
    public HtmlReader(Logger logger) {
        this.logger = logger;
    }

    public Optional<String> readHtmlFile(String uri) {
        InputStream inputStream;
        if (uri == null || (inputStream = getClass().getResourceAsStream(uri)) == null) {
            logger.log(Level.SEVERE, "File not found or not accessible: {0}", uri);
            return Optional.empty();
        }

        String result = null;
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, HTML_CHARSET))) {
            result = reader.lines().collect(Collectors.joining(HTML_LINE_SEPARATOR));
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading file " + uri, e);
        }

        return Optional.of(result);
    }
}
