package metube.web;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public final class WebConstants {

    public static final Charset SERVER_ENCODING = StandardCharsets.UTF_8;
    public static final String SERVER_ENCODING_STR = SERVER_ENCODING.name();

    public static final String HTTP_METHOD_POST = "POST";

    public static final String ATTRIBUTE_MODEL = "model";
    public static final String ATTRIBUTE_USERNAME = "username";

    public static final String URL_INDEX_SERVLET = "";
    public static final String URL_INDEX = "/";

    private static final String URL_USER_BASE = "/user";
    public static final String URL_USER_HOME = URL_USER_BASE + "/home";
    public static final String URL_USER_LOGIN = URL_USER_BASE + "/login";
    public static final String URL_USER_REGISTER = URL_USER_BASE + "/register";
    public static final String URL_USER_PROFILE = URL_USER_BASE + "/profile";
    public static final String URL_USER_LOGOUT = URL_USER_BASE + "/logout";

    private static final String URL_TUBE_BASE = "/tube";
    public static final String URL_TUBE_UPLOAD = URL_TUBE_BASE + "/upload";
    public static final String URL_TUBES_DETAILS = URL_TUBE_BASE + "/details";
    public static final String URL_TUBES_UPLOAD = URL_TUBE_BASE + "/upload";

    private static final String JSP_BASE_LOCATION = "/WEB-INF/jsps";

    public static final String JSP_INDEX = JSP_BASE_LOCATION + "/index.jsp";

    private static final String JSP_TUBE_BASE_LOCATION = JSP_BASE_LOCATION + URL_TUBE_BASE;
    public static final String JSP_TUBE_DETAILS = JSP_TUBE_BASE_LOCATION + "/details.jsp";
    public static final String JSP_TUBE_UPLOAD = JSP_TUBE_BASE_LOCATION + "/upload.jsp";

    private static final String JSP_USER_BASE_LOCATION = JSP_BASE_LOCATION + URL_USER_BASE;
    public static final String JSP_USER_HOME = JSP_USER_BASE_LOCATION + "/home.jsp";
    public static final String JSP_USER_LOGIN = JSP_USER_BASE_LOCATION + "/login.jsp";
    public static final String JSP_USER_PROFILE = JSP_USER_BASE_LOCATION + "/profile.jsp";
    public static final String JSP_USER_REGISTER = JSP_USER_BASE_LOCATION + "/register.jsp";

    private static final String JSP_TEMPLATES_BASE_LOCATION = JSP_BASE_LOCATION + "/templates";
    public static final String JSP_TEMPLATE_HEAD = JSP_TEMPLATES_BASE_LOCATION + "/head.jsp";
    public static final String JSP_TEMPLATE_HEADER = JSP_TEMPLATES_BASE_LOCATION + "/header.jsp";
    public static final String JSP_TEMPLATE_FOOTER = JSP_TEMPLATES_BASE_LOCATION + "/footer.jsp";

    private WebConstants() {
    }
}
