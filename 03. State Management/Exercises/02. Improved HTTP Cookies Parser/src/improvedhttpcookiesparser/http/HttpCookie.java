package improvedhttpcookiesparser.http;

public class HttpCookie {

    private String key;
    private String value;

    public HttpCookie(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
