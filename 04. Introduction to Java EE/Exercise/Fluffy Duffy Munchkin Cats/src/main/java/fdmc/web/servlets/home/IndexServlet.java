package fdmc.web.servlets.home;

import fdmc.utils.htmlbuilder.HtmlBuilder;
import fdmc.web.servlets.BaseServlet;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("")
public class IndexServlet extends BaseServlet {

    private static final long serialVersionUID = 1L;

    private static final String URI_HOME_HTML = "/html/templates/home/home.html";

    private static final Logger LOGGER = Logger.getLogger(IndexServlet.class.getName());

    @Inject
    public IndexServlet(HtmlBuilder htmlBuilder) {
        super(htmlBuilder);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            handleResponse(resp, Map.of(HTML_SKELETON_BODY_PLACEHOLDER, URI_HOME_HTML), Collections.emptyMap());
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, req.getRequestURL().toString(), e);
        }
    }
}
