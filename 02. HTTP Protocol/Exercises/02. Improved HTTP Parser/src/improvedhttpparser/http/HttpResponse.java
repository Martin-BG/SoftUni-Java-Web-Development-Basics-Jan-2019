package improvedhttpparser.http;

import java.util.Map;

public interface HttpResponse {

    Map<String, String> getHeaders();

    HttpStatus getHttpStatus();

    void setHttpStatus(HttpStatus httpStatus);

    byte[] getContent();

    void setContent(byte[] content);

    byte[] getBytes();

    void addHeader(String header, String value);
}
