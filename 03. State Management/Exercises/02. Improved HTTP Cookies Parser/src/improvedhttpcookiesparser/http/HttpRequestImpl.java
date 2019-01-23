package improvedhttpcookiesparser.http;

import java.util.*;
import java.util.regex.Pattern;

public class HttpRequestImpl implements HttpRequest {

    private static final Pattern LINE_SPLIT_PATTERN = Pattern.compile(HttpConstants.HTTP_LINE_SEPARATOR);
    private static final Pattern REQUEST_LINE_SPLIT_PATTERN = Pattern.compile(HttpConstants.REQUEST_LINE_SEPARATOR);
    private static final Pattern HEADERS_SPLIT_PATTERN = Pattern.compile(HttpConstants.HEADERS_SEPARATOR);
    private static final Pattern PARAMS_DELIMITER_SPLIT_PATTERN = Pattern.compile(HttpConstants.PARAMS_DELIMITER);
    private static final Pattern PARAMS_SPLIT_PATTERN = Pattern.compile(HttpConstants.PARAMS_SEPARATOR);

    private Map<String, String> headers;
    private Map<String, String> bodyParameters;
    private String method;
    private String requestUrl;

    public HttpRequestImpl(String request) {
        headers = new LinkedHashMap<>();
        bodyParameters = new HashMap<>();
        parseRequest(request);
    }

    private void parseRequest(String request) {
        List<String> lines = Arrays.asList(LINE_SPLIT_PATTERN.split(request, -1));
        if (lines.isEmpty()) {
            return;
        }

        String[] requestLineTokens = REQUEST_LINE_SPLIT_PATTERN.split(lines.get(0));
        setMethod(requestLineTokens[0]);
        setRequestUrl(requestLineTokens[1]);

        for (int lineIndex = 1; lineIndex < lines.size() && !lines.get(lineIndex).isEmpty(); lineIndex++) {
            String[] headerKvp = HEADERS_SPLIT_PATTERN.split(lines.get(lineIndex));
            addHeader(headerKvp[0], headerKvp[1]);
        }

        if (!lines.get(lines.size() - 1).isEmpty()) {
            Arrays.stream(PARAMS_DELIMITER_SPLIT_PATTERN.split(lines.get(lines.size() - 1)))
                    .map(PARAMS_SPLIT_PATTERN::split)
                    .forEach(parameterKvp -> addBodyParameter(parameterKvp[0], parameterKvp[1]));
        }
    }

    @Override
    public Map<String, String> getHeaders() {
        return Collections.unmodifiableMap(headers);
    }

    @Override
    public Map<String, String> getBodyParameters() {
        return Collections.unmodifiableMap(bodyParameters);
    }

    @Override
    public String getMethod() {
        return method;
    }

    @Override
    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String getRequestUrl() {
        return requestUrl;
    }

    @Override
    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    @Override
    public void addHeader(String header, String value) {
        headers.put(header, value);
    }

    @Override
    public void addBodyParameter(String parameter, String value) {
        bodyParameters.put(parameter, value);
    }

    @Override
    public boolean isResource() {
        return requestUrl.contains(".");
    }
}
