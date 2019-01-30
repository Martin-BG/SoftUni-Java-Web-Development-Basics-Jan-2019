package fdmc.web.servlets;

import fdmc.domain.entities.Cat;
import fdmc.utils.Reader;
import fdmc.utils.TemplateEngine;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class BaseServlet extends HttpServlet {

    protected static final String PARAM_CAT_NAME = "catName";
    protected static final String PARAM_CAT_BREED = "breed";
    protected static final String PARAM_CAT_COLOR = "color";
    protected static final String PARAM_CAT_AGE = "age";
    protected static final String HTML_SKELETON_URI = "/html/index.html";
    protected static final String HTML_SKELETON_BODY_PLACEHOLDER = "body";
    private static final Logger LOGGER = Logger.getLogger(BaseServlet.class.getName());
    private static final String ATTRIBUTE_CATS_NAME = "cats";
    protected final Reader fileReader;
    protected final TemplateEngine templateEngine;

    protected BaseServlet(Reader fileReader, TemplateEngine templateEngine) {
        this.fileReader = fileReader;
        this.templateEngine = templateEngine;
    }

    protected static void notFound(HttpServletResponse resp, String resource) {
        String message = "Resource: " + resource;
        try {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, message);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, message, e);
        }
    }

    protected void loadSimplePage(HttpServletResponse resp, String uri) throws IOException {
        try (PrintWriter writer = resp.getWriter()) {
            Optional<String> stringOptional = fileReader.read(uri);
            stringOptional.ifPresentOrElse(writer::write, () -> notFound(resp, uri));
        }
    }

    protected void loadAndBuildPage(HttpServletResponse resp,
                                    String skeletonUri,
                                    Map<String, String> templatesUris,
                                    Map<String, String> params) throws IOException {
        try (PrintWriter writer = resp.getWriter()) {
            Optional<String> skeleton = fileReader.read(skeletonUri);
            Optional<Map<String, String>> templates = fileReader.read(templatesUris);

            if (skeleton.isPresent() && templates.isPresent()) {
                String html = templateEngine.applyTemplates(skeleton.get(), templates.get());
                html = templateEngine.applyTemplates(html, params);
                writer.write(html);
            } else {
                notFound(resp, skeletonUri);
            }
        }
    }

    @SuppressWarnings("unchecked")
    protected Map<String, Cat> getCats() {
        if (this.getServletContext().getAttribute(ATTRIBUTE_CATS_NAME) == null) {
            this.getServletContext().setAttribute(ATTRIBUTE_CATS_NAME, new ConcurrentHashMap<String, Cat>());
        }

        return (ConcurrentHashMap<String, Cat>) this.getServletContext().getAttribute(ATTRIBUTE_CATS_NAME);
    }
}
