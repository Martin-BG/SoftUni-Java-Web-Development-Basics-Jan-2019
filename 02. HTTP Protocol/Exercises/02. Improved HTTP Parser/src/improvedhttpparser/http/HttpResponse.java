package improvedhttpparser.http;

import java.util.Map;

public interface HttpResponse {

    Map<String, String> getHeaders();

    StatusCode getStatusCode();

    void setStatusCode(StatusCode statusCode);

    byte[] getContent();

    void setContent(byte[] content);

    byte[] getBytes();

    void addHeader(String header, String value);
}
