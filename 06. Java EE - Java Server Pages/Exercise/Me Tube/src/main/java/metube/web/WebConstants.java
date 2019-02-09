package metube.web;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public final class WebConstants {

    public static final Charset SERVER_ENCODING = StandardCharsets.UTF_8;
    public static final String SERVER_ENCODING_STR = SERVER_ENCODING.name();

    public static final String ATTRIBUTE_TUBES = "tubes";
    public static final String ATTRIBUTE_MODEL = "model";
    public static final String ATTRIBUTE_NAME = "name";

    public static final String URL_INDEX = "";
    public static final String URL_HOME = "/";
    private static final String URL_TUBES_BASE = "/tubes";
    public static final String URL_TUBES_ALL = URL_TUBES_BASE + "/all";
    public static final String URL_TUBES_DETAILS = URL_TUBES_BASE + "/details";
    public static final String URL_TUBES_CREATE = URL_TUBES_BASE + "/create";
    public static final String URL_TUBES_DETAILS_ATR_NAME = URL_TUBES_BASE + "/details?" + ATTRIBUTE_NAME + "=";

    private static final String JSP_BASE_LOCATION = "/WEB-INF/jsps";
    public static final String JSP_INDEX = JSP_BASE_LOCATION + "/index.jsp";
    public static final String JSP_TUBE_DETAILS = JSP_BASE_LOCATION + "/details-tube.jsp";
    public static final String JSP_TUBE_ALL = JSP_BASE_LOCATION + "/all-tubes.jsp";
    public static final String JSP_TUBE_CREATE = JSP_BASE_LOCATION + "/create-tube.jsp";

    private static final String JSP_TEMPLATES_BASE_LOCATION = "/WEB-INF/jsps/templates";
    public static final String JSP_TEMPLATE_HEAD = JSP_TEMPLATES_BASE_LOCATION + "/head.jsp";
    public static final String JSP_TEMPLATE_FOOTER = JSP_TEMPLATES_BASE_LOCATION + "/footer.jsp";

    public static final String HTTP_METHOD_POST = "POST";

    private WebConstants() {
    }
}
