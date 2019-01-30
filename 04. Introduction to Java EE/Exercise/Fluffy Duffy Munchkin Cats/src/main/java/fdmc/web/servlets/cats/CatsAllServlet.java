package fdmc.web.servlets.cats;


import fdmc.domain.entities.Cat;
import fdmc.utils.Reader;
import fdmc.utils.TemplateEngine;
import fdmc.web.servlets.BaseServlet;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
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
    public CatsAllServlet(Reader htmlFileReader, TemplateEngine templateEngine) {
        super(htmlFileReader, templateEngine);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, Cat> cats = getCats();
        try {
            Map<String, String> templatesUris = new LinkedHashMap<>();
            Map<String, String> params = new LinkedHashMap<>();
            templatesUris.put(HTML_SKELETON_BODY_PLACEHOLDER, URI_CATS_ALL_HTML);
            if (cats.isEmpty()) {
                templatesUris.put(CATS_PLACEHOLDER, URI_CATS_ALL_NO_CATS_HTML);
            } else {
                Optional<String> catLinkHtml = fileReader.read(URI_CATS_ALL_CAT_LINK_HTML);
                if (catLinkHtml.isPresent()) {
                    StringBuilder catsLinks = new StringBuilder();
                    cats.forEach((name, cat) -> catsLinks.append(
                            templateEngine.applyTemplates(catLinkHtml.get(), Map.of(PARAM_CAT_NAME, name))));
                    params.put(CATS_PLACEHOLDER, catsLinks.toString());
                } else {
                    notFound(resp, URI_CATS_ALL_CAT_LINK_HTML);
                }
            }
            loadAndBuildPage(resp, HTML_SKELETON_URI, templatesUris, params);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, req.getRequestURL().toString(), e);
        }
    }
}
