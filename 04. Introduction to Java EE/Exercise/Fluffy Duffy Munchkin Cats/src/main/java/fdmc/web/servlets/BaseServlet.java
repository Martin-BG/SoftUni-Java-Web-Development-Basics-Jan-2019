package fdmc.web.servlets;

import fdmc.domain.entities.Cat;
import fdmc.utils.htmlbuilder.HtmlBuilder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class BaseServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(BaseServlet.class.getName());

    private static final String ATTRIBUTE_CATS_NAME = "cats";

    protected static final String PARAM_CAT_NAME = "catName";
    protected static final String PARAM_CAT_BREED = "breed";
    protected static final String PARAM_CAT_COLOR = "color";
    protected static final String PARAM_CAT_AGE = "age";

    protected static final String HTML_SKELETON_BODY_PLACEHOLDER = "body";

    protected final HtmlBuilder htmlBuilder;

    protected BaseServlet(HtmlBuilder htmlBuilder) {
        this.htmlBuilder = htmlBuilder;
    }

    protected static Runnable notFound(HttpServletResponse resp, String resource) {
        return () -> {
            String message = "Resource(s) not found:\r\n\t" + resource;
            LOGGER.log(Level.SEVERE, message);
            try {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, message);
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, message, e);
            }
        };
    }

    protected static Runnable badRequest(HttpServletResponse resp, String msg) {
        return () -> {
            String message = "Bad request:\r\n\t" + msg;
            LOGGER.log(Level.SEVERE, message);
            try {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, message);
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, message, e);
            }
        };
    }

    protected static void redirect(HttpServletResponse resp, String url) {
        try {
            resp.sendRedirect(url);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, url, e);
        }
    }

    protected void handleResponse(HttpServletResponse resp,
                                  Map<String, String> templatesUris,
                                  Map<String, String> params) {
        try (PrintWriter writer = resp.getWriter()) {
            htmlBuilder
                    .buildFrom(templatesUris, params)
                    .ifPresentOrElse(
                            writer::write,
                            notFound(resp, String.join("\r\n\t", templatesUris.values())));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error", e);
        }
    }

    @SuppressWarnings("unchecked")
    private Map<String, Cat> getCats() {
        // ServletContext Storage - for all clients - http://tutorials.jenkov.com/java-servlets/servletcontext.html
        // Session Storage - for single client - http://tutorials.jenkov.com/java-servlets/httpsession.html
        // #81 - https://javabeat.net/servlets-interview-questions/
        // https://stackoverflow.com/questions/35837285/different-ways-to-get-servlet-context/35840742#35840742
        if (getServletContext().getAttribute(ATTRIBUTE_CATS_NAME) == null) {
            getServletContext().setAttribute(ATTRIBUTE_CATS_NAME, new ConcurrentHashMap<String, Cat>());
        }

        return (Map<String, Cat>) getServletContext().getAttribute(ATTRIBUTE_CATS_NAME);
    }

    protected Map<String, Cat> allCats() {
        return Collections.unmodifiableMap(getCats());
    }

    protected void addCat(Cat cat) {
        getCats().put(cat.getName(), cat);
    }

    protected Optional<Cat> findCat(String name) {
        return Optional.ofNullable(getCats().get(name));
    }
}
