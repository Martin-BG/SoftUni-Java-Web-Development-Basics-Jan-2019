package chushka.web.servlets;


import chushka.domain.models.service.ProductServiceModel;
import chushka.utils.htmlbuilder.HtmlBuilder;
import chushka.utils.templatebuilder.TemplateBuilder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class BaseServlet extends HttpServlet {

    protected static final String HTML_SKELETON_BODY_PLACEHOLDER = "body";
    private static final Logger LOGGER = Logger.getLogger(BaseServlet.class.getName());
    private static final String HTML_NAME_PLACEHOLDER = "name";
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

    protected static String getProductsHtmlList(String html, List<ProductServiceModel> products) {
        StringBuilder itemsList = new StringBuilder();
        products.forEach(product -> itemsList.append(
                TemplateBuilder
                        .from(html)
                        .put(HTML_NAME_PLACEHOLDER, product.getName())
                        .build()));
        return itemsList.toString();
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
}
