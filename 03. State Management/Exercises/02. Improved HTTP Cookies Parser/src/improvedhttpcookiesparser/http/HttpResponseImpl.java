package improvedhttpcookiesparser.http;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class HttpResponseImpl implements HttpResponse {

    private Map<String, String> headers;
    private byte[] content;
    private HttpStatus httpStatus;

    public HttpResponseImpl() {
        content = new byte[0];
        headers = new LinkedHashMap<>();
    }

    @Override
    public Map<String, String> getHeaders() {
        return Collections.unmodifiableMap(headers);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
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
                .append(HttpConstants.HTTP_VERSION)
                .append(HttpConstants.REQUEST_LINE_SEPARATOR)
                .append(httpStatus.getCode())
                .append(HttpConstants.REQUEST_LINE_SEPARATOR)
                .append(httpStatus.getName())
                .append(HttpConstants.HTTP_LINE_SEPARATOR);
        headers.forEach((key, value) -> response
                .append(key)
                .append(HttpConstants.HEADERS_SEPARATOR)
                .append(value)
                .append(HttpConstants.HTTP_LINE_SEPARATOR));
        response.append(HttpConstants.HTTP_LINE_SEPARATOR)
                .append(new String(content, HttpConstants.CHARSET));

        return response.toString().getBytes(HttpConstants.CHARSET);
    }

    @Override
    public void addHeader(String header, String value) {
        headers.put(header, value);
    }
}
