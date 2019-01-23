package improvedhttpcookiesparser.http;

import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum HttpMethod {
    GET, HEAD, POST, PUT, DELETE, CONNECT, OPTIONS, TRACE, PATCH;

    private static final Map<String, HttpMethod> ENUM_MAP = Stream.of(HttpMethod.values())
            .collect(Collectors.toUnmodifiableMap(Enum::name, m -> m));

    public static HttpMethod get(String methodName) {
        HttpMethod httpMethod = ENUM_MAP.get(methodName.toUpperCase(Locale.ENGLISH));

        if (httpMethod == null) {
            throw new IllegalArgumentException("Unknown HTTP Method: " + methodName);
        }

        return httpMethod;
    }
}
