package metube.web;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public final class WebConstants {

    public static final Charset SERVER_ENCODING = StandardCharsets.UTF_8;
    public static final String SERVER_ENCODING_STR = SERVER_ENCODING.name();
    public static final String ATTRIBUTE_TUBES = "tubes";
    public static final String ATTRIBUTE_MODEL = "model";
    public static final String ATTRIBUTE_NAME = "name";
    public static final String URL_TUBES_ALL = "/tubes/all";
    public static final String URL_INDEX = "";
    public static final String URL_TUBES_DETAILS = "/tubes/details";
    public static final String URL_TUBES_CREATE = "/tubes/create";
    public static final String URL_TUBES_DETAILS_ATR_NAME = "/tubes/details?" + ATTRIBUTE_NAME + "=";
    public static final String HTTP_METHOD_POST = "POST";
    private static final String BASE_JSP_LOCATION = "/WEB-INF/jsps";
    public static final String JSP_INDEX = BASE_JSP_LOCATION + "/index.jsp";
    public static final String JSP_TUBE_DETAILS = BASE_JSP_LOCATION + "/details-tube.jsp";
    public static final String JSP_TUBE_ALL = BASE_JSP_LOCATION + "/all-tubes.jsp";
    public static final String JSP_TUBE_CREATE = BASE_JSP_LOCATION + "/create-tube.jsp";


    private WebConstants() {
    }
}
