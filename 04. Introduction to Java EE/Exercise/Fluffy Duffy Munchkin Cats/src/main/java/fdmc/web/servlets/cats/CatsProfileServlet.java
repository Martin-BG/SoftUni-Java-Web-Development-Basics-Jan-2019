package fdmc.web.servlets.cats;


import fdmc.domain.entities.Cat;
import fdmc.utils.htmlbuilder.HtmlBuilder;
import fdmc.web.servlets.BaseServlet;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/cats/profile")
public class CatsProfileServlet extends BaseServlet {

    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger.getLogger(CatsProfileServlet.class.getName());

    private static final String URI_CATS_PROFILE_HTML = "/html/templates/cats/profile.html";
    private static final String URI_CATS_NOT_FOUND_HTML = "/html/templates/cats/profile-not-found.html";

    @Inject
    public CatsProfileServlet(HtmlBuilder htmlBuilder) {
        super(htmlBuilder);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String catName = req.getParameter(PARAM_CAT_NAME);
            if (catName == null) {
                LOGGER.log(Level.SEVERE, "No catName supplied: " + req.getQueryString());
                badRequest(resp, "No catName supplied").run();
                return;
            }

            Cat cat = getCats().get(catName);
            if (cat != null) {
                handleResponse(resp,
                        Map.of(HTML_SKELETON_BODY_PLACEHOLDER, URI_CATS_PROFILE_HTML),
                        Map.of(PARAM_CAT_NAME, cat.getName(),
                                PARAM_CAT_AGE, Integer.toString(cat.getAge()),
                                PARAM_CAT_BREED, cat.getBreed(),
                                PARAM_CAT_COLOR, cat.getColor()));
            } else {
                handleResponse(resp,
                        Map.of(HTML_SKELETON_BODY_PLACEHOLDER, URI_CATS_NOT_FOUND_HTML),
                        Map.of(PARAM_CAT_NAME, catName));
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, req.getRequestURL().toString(), e);
        }
    }
}
