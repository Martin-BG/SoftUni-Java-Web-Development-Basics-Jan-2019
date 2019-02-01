package fdmc.web.servlets.cats;


import fdmc.domain.entities.Cat;
import fdmc.utils.htmlbuilder.HtmlBuilder;
import fdmc.web.servlets.BaseServlet;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/cats/create")
public class CatsCreateServlet extends BaseServlet {

    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger.getLogger(CatsCreateServlet.class.getName());

    private static final String URI_CATS_CREATE_HTML = "/html/templates/cats/create.html";
    private static final String URL_CATS_PROFILE_CAT_NAME = "/cats/profile?" + PARAM_CAT_NAME + "=";

    @Inject
    public CatsCreateServlet(HtmlBuilder htmlBuilder) {
        super(htmlBuilder);
    }

    private static Optional<Cat> makeCatFromRequest(HttpServletRequest req) {
        Cat cat = null;

        try {
            String name = req.getParameter(PARAM_CAT_NAME);
            String breed = req.getParameter(PARAM_CAT_BREED);
            String color = req.getParameter(PARAM_CAT_COLOR);
            int age = Integer.parseInt(req.getParameter(PARAM_CAT_AGE));

            if (name != null && breed != null && color != null && age >= 0) {
                cat = new Cat(name, breed, color, age);
            }
        } catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "Invalid cat age", e);
        }

        return Optional.ofNullable(cat);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        handleResponse(resp,
                Map.of(HTML_SKELETON_BODY_PLACEHOLDER, URI_CATS_CREATE_HTML),
                Map.of("catName", PARAM_CAT_NAME,
                        "breed", PARAM_CAT_BREED,
                        "color", PARAM_CAT_COLOR,
                        "age", PARAM_CAT_AGE));

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        makeCatFromRequest(req)
                .ifPresentOrElse(
                        cat -> {
                            addCat(cat);
                            redirect(resp, URL_CATS_PROFILE_CAT_NAME + cat.getName());
                        },
                        badRequest(resp, "Invalid cat parameters"));
    }
}
