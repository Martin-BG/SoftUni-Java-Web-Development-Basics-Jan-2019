package improvedhttpcookiesparser.http;

import java.util.List;
import java.util.Map;

public interface HttpRequest {

    Map<String, String> getHeaders();

    Map<String, String> getBodyParameters();

    List<HttpCookie> getCookies();

    String getMethod();

    void setMethod(String method);

    String getRequestUrl();

    void setRequestUrl(String requestUrl);

    void addHeader(String header, String value);

    void addBodyParameter(String parameter, String value);

    void addCookie(String key, String value);

    boolean isResource();
}
