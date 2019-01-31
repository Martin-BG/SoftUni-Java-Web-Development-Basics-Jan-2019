package fdmc.web.servlets.cats;


import fdmc.domain.entities.Cat;
import fdmc.utils.htmlbuilder.HtmlBuilder;
import fdmc.utils.templatebuilder.TemplateBuilder;
import fdmc.web.servlets.BaseServlet;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/cats/all")
public class CatsAllServlet extends BaseServlet {

    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger.getLogger(CatsAllServlet.class.getName());

    private static final String URI_CATS_ALL_HTML = "/html/templates/cats/all.html";
    private static final String URI_CATS_ALL_NO_CATS_HTML = "/html/templates/cats/all-no-cats.html";
    private static final String URI_CATS_ALL_CAT_LINK_HTML = "/html/templates/cats/all-cat-link.html";
    private static final String CATS_PLACEHOLDER = "cats";

    @Inject
    public CatsAllServlet(HtmlBuilder htmlBuilder) {
        super(htmlBuilder);
    }

    private static Consumer<String> buildCatsList(Map<String, Cat> cats, Map<String, String> params) {
        return html -> {
            StringBuilder catsLinks = new StringBuilder();
            cats.forEach((name, cat) ->
                    catsLinks.append(
                            TemplateBuilder
                                    .from(html)
                                    .put(PARAM_CAT_NAME, name)
                                    .build()));
            params.put(CATS_PLACEHOLDER, catsLinks.toString());
        };
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Map<String, String> params = new LinkedHashMap<>();
            Map<String, String> templatesUris = new LinkedHashMap<>();

            templatesUris.put(HTML_SKELETON_BODY_PLACEHOLDER, URI_CATS_ALL_HTML);

            Map<String, Cat> cats = getCats();

            if (cats.isEmpty()) {
                templatesUris.put(CATS_PLACEHOLDER, URI_CATS_ALL_NO_CATS_HTML);
            } else {
                htmlBuilder
                        .buildFrom(URI_CATS_ALL_CAT_LINK_HTML)
                        .ifPresentOrElse(
                                buildCatsList(cats, params),
                                notFound(resp, URI_CATS_ALL_CAT_LINK_HTML));
            }

            handleResponse(resp, templatesUris, params);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, req.getRequestURL().toString(), e);
        }
    }
}
