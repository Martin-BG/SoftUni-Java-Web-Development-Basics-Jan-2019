package chushka.utils.reader;

import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class HtmlFileReader implements Reader {

    private static final String HTML_LINE_SEPARATOR = "\r\n";
    private static final Charset HTML_CHARSET = StandardCharsets.UTF_8;

    private final Logger logger;

    @Inject
    public HtmlFileReader(Logger logger) {
        this.logger = logger;
    }

    @Override
    public Optional<String> read(String uri) {
        InputStream inputStream;
        String content = null;

        if (uri != null && (inputStream = getClass().getResourceAsStream(uri)) != null) {
            try (final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, HTML_CHARSET))) {
                content = reader.lines().collect(Collectors.joining(HTML_LINE_SEPARATOR));
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Error reading file " + uri, e);
            }
        } else {
            logger.log(Level.SEVERE, "File not found or not accessible: {0}", uri);
        }

        return Optional.ofNullable(content);
    }

    @Override
    public Optional<Map<String, String>> read(Map<String, String> uris) {
        Map<String, String> templates = new LinkedHashMap<>();
        uris.forEach((name, uri) -> read(uri).ifPresent(content -> templates.put(name, content)));

        if (templates.size() == uris.size()) {
            return Optional.of(templates);
        }

        return Optional.empty();
    }
}
