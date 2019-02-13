package metube.web.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class ServletUtil {

    private static final Logger LOG = Logger.getLogger(ServletUtil.class.getName());

    private ServletUtil() {
    }

    public static void forward(HttpServletRequest req, HttpServletResponse resp, String jsp) {
        try {
            req.getRequestDispatcher(jsp).forward(req, resp);
        } catch (ServletException | IOException e) {
            LOG.log(Level.SEVERE, "Failed forward to " + jsp, e);
        }
    }

    public static void redirect(HttpServletResponse resp, String url) {
        try {
            resp.sendRedirect(url);
        } catch (IOException e) {
            LOG.log(Level.SEVERE, "Failed redirect to " + url, e);
        }
    }

    public static void error(HttpServletResponse resp, int httpErrorCode, String message) {
        try {
            resp.sendError(httpErrorCode, message);
        } catch (IOException e) {
            LOG.log(Level.SEVERE, "Failed sending error \'" + message + "\'", e);
        }
    }
}
