package improvedhttpparser.http;

public enum HttpStatus {
    OK(200, "OK", "Success"),

    BAD_REQUEST(400, "Bad Request",
            "There was an error with the requested functionality due to malformed request."),

    UNAUTHORIZED(401, "Unauthorized",
            "You are not authorized to access the requested functionality."),

    NOT_FOUND(404, "Not Found",
            "The requested functionality was not found.");

    private int code;
    private String name;
    private String description;

    HttpStatus(int code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
