package chushka.web.servlets;

import chushka.domain.entities.enums.Type;
import chushka.domain.models.service.ProductServiceModel;
import chushka.service.ProductService;
import chushka.utils.htmlbuilder.HtmlBuilder;
import chushka.utils.reader.HtmlFileReader;
import chushka.utils.templatebuilder.TemplateBuilder;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;

@WebServlet("/products/create")
public class ProductCreateServlet extends BaseServlet {

    private static final long serialVersionUID = 1L;

    private static final String URI_PRODUCT_CREATE_HTML = "/html/templates/product/create.html";
    private static final String URI_TYPE_OPTION_HTML = "/html/templates/product/type-option.html";
    private static final String URL_PRODUCTS_ALL = "/products/all";
    private static final String HTML_TYPE_PLACEHOLDER = "type";
    private static final String HTML_OPTIONS_PLACEHOLDER = "options";
    private static final String PARAM_NAME = "name";
    private static final String PARAM_DESCRIPTION = "description";

    private final ProductService productService;
    private final HtmlFileReader htmlFileReader;

    @Inject
    public ProductCreateServlet(ProductService productService,
                                HtmlFileReader htmlFileReader,
                                HtmlBuilder htmlBuilder) {
        super((htmlBuilder));
        this.productService = productService;
        this.htmlFileReader = htmlFileReader;
    }

    private static ProductServiceModel getProductServiceModel(HttpServletRequest req) {
        ProductServiceModel productServiceModel = new ProductServiceModel();
        productServiceModel.setName(req.getParameter(PARAM_NAME));
        String tp = req.getParameter(HTML_TYPE_PLACEHOLDER);
        productServiceModel.setType(Type.fromLabel(tp));
        productServiceModel.setDescription(req.getParameter(PARAM_DESCRIPTION));
        return productServiceModel;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        htmlFileReader
                .read(URI_TYPE_OPTION_HTML)
                .ifPresentOrElse(html -> {
                    StringBuilder options = new StringBuilder();
                    Arrays.stream(Type.values())
                            .forEach(type -> options.append(
                                    TemplateBuilder
                                            .from(html)
                                            .put(HTML_TYPE_PLACEHOLDER, type.getLabel())
                                            .build()));
                    handleResponse(resp,
                            Map.of(HTML_SKELETON_BODY_PLACEHOLDER, URI_PRODUCT_CREATE_HTML),
                            Map.of(HTML_OPTIONS_PLACEHOLDER, options.toString()));
                }, notFound(resp, URI_TYPE_OPTION_HTML));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            ProductServiceModel productServiceModel = getProductServiceModel(req);
            productService.saveProduct(productServiceModel);
            redirect(resp, URL_PRODUCTS_ALL);
        } catch (RuntimeException e) {
            badRequest(resp, "Invalid input parameters\n" + e.getCause().getLocalizedMessage()).run();
        }
    }
}
