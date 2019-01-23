package improvedhttpcookiesparser.http;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public final class HttpConstants {

    public static final Charset CHARSET = StandardCharsets.UTF_8;

    public static final String HTTP_LINE_SEPARATOR = "\r\n";
    public static final String HTTP_VERSION = "HTTP/1.1";
    public static final String HEADERS_SEPARATOR = ": ";
    public static final String PARAMS_SEPARATOR = "&";
    public static final String PARAM_PAIR_SEPARATOR = "=";
    public static final String REQUEST_LINE_SEPARATOR = " ";
    public static final String COOKIES_SEPARATOR = "; ";
    public static final String COOKIE_PAIR_SEPARATOR = "=";
    public static final String HEADER_COOKIE = "Cookie";

    private HttpConstants() {
    }
}
