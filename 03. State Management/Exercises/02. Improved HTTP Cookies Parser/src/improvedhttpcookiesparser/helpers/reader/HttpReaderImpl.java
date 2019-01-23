package improvedhttpcookiesparser.helpers.reader;

import improvedhttpcookiesparser.http.HttpConstants;

import java.io.BufferedReader;
import java.io.IOException;

public class HttpReaderImpl implements HttpReader {

    private final BufferedReader bufferedReader;

    public HttpReaderImpl(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    @Override
    public String readLine() throws IOException {
        return bufferedReader.readLine();
    }

    @Override
    public String readHttpRequest() throws IOException {
        StringBuilder request = new StringBuilder();
        String line;
        while ((line = readLine()) != null) {
            request.append(line).append(HttpConstants.HTTP_LINE_SEPARATOR);
            if (line.isEmpty()) {
                break;
            }
        }
        request.append(readLine());
        return request.toString();
    }
}
