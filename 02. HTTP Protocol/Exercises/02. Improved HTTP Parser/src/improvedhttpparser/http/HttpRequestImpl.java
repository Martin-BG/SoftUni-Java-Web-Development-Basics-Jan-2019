package improvedhttpparser.http;

import java.util.*;
import java.util.regex.Pattern;

public class HttpRequestImpl implements HttpRequest {

    private static final Pattern LINE_SPLIT_PATTERN = Pattern.compile(Constants.HTTP_LINE_SEPARATOR);
    private static final Pattern REQUEST_LINE_SPLIT_PATTERN = Pattern.compile(Constants.REQUEST_LINE_SEPARATOR);
    private static final Pattern HEADERS_SPLIT_PATTERN = Pattern.compile(Constants.HEADERS_SEPARATOR);
    private static final Pattern PARAMS_DELIMITER_SPLIT_PATTERN = Pattern.compile(Constants.PARAMS_DELIMITER);
    private static final Pattern PARAMS_SPLIT_PATTERN = Pattern.compile(Constants.PARAMS_SEPARATOR);

    private Map<String, String> headers;
    private Map<String, String> bodyParameters;
    private String method;
    private String requestUrl;
    private boolean isResource;

    public HttpRequestImpl(String request) {
        headers = new HashMap<>();
        bodyParameters = new HashMap<>();
        parseRequest(request);
    }

    private void parseRequest(String request) {
        List<String> lines = Arrays.asList(LINE_SPLIT_PATTERN.split(request));
        if (lines.isEmpty()) {
            return;
        }

        String[] requestLineTokens = REQUEST_LINE_SPLIT_PATTERN.split(lines.get(0));
        method = requestLineTokens[0];
        requestUrl = requestLineTokens[1];
        isResource = requestUrl.contains(".");

        int lineIndex = 1;
        for (; lineIndex < lines.size() && !lines.get(lineIndex).isEmpty(); lineIndex++) {
            String[] kvp = HEADERS_SPLIT_PATTERN.split(lines.get(lineIndex));
            addHeader(kvp[0], kvp[1]);
        }

        if (lineIndex == lines.size() - 2) {
            Arrays.stream(PARAMS_DELIMITER_SPLIT_PATTERN.split(lines.get(lines.size() - 1)))
                    .map(PARAMS_SPLIT_PATTERN::split)
                    .forEach(kvp -> addBodyParameter(kvp[0], kvp[1]));
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
        return isResource;
    }
}
