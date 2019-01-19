package improvedhttpparser.http;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class HttpResponseImpl implements HttpResponse {

    private Map<String, String> headers;
    private byte[] content;
    private StatusCode statusCode;

    public HttpResponseImpl() {
        content = new byte[0];
        headers = new HashMap<>();
    }

    @Override
    public Map<String, String> getHeaders() {
        return Collections.unmodifiableMap(headers);
    }

    @Override
    public StatusCode getStatusCode() {
        return statusCode;
    }

    @Override
    public void setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public byte[] getContent() {
        return content.clone();
    }

    @Override
    public void setContent(byte[] content) {
        this.content = content.clone();
    }

    @Override
    public byte[] getBytes() {
        StringBuilder response = new StringBuilder()
                .append(Constants.HTTP_VERSION)
                .append(Constants.REQUEST_LINE_SEPARATOR)
                .append(statusCode.getCode())
                .append(Constants.REQUEST_LINE_SEPARATOR)
                .append(statusCode.getName())
                .append(Constants.HTTP_LINE_SEPARATOR);
        headers.forEach((key, value) -> response
                .append(key)
                .append(Constants.HEADERS_SEPARATOR)
                .append(value)
                .append(Constants.HTTP_LINE_SEPARATOR));
        response.append(Constants.HTTP_LINE_SEPARATOR)
                .append(new String(content, Constants.CHARSET));

        return response.toString().getBytes(Constants.CHARSET);
    }

    @Override
    public void addHeader(String header, String value) {
        headers.put(header, value);
    }
}
