package chushka.web.servlets;

import chushka.domain.models.view.ProductVewModel;
import chushka.service.ProductService;
import chushka.utils.htmlbuilder.HtmlBuilder;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

@WebServlet("/products/details")
public class ProductDetailsServlet extends BaseServlet {

    private static final long serialVersionUID = 1L;

    private static final String URI_PRODUCT_DETAILS_HTML = "/html/templates/product/details.html";
    private static final String PARAM_NAME = "name";
    private static final String PARAM_DESCRIPTION = "description";
    private static final String PARAM_TYPE = "type";

    private final ProductService productService;

    @Inject
    public ProductDetailsServlet(ProductService productService, HtmlBuilder htmlBuilder) {
        super(htmlBuilder);
        this.productService = productService;
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter(PARAM_NAME);
        Optional<ProductVewModel> product = productService.findByName(name);
        if (product.isPresent()) {
            handleResponse(resp,
                    Map.of(HTML_SKELETON_BODY_PLACEHOLDER, URI_PRODUCT_DETAILS_HTML),
                    Map.of(PARAM_NAME, product.get().getName(),
                            PARAM_DESCRIPTION, product.get().getDescription(),
                            PARAM_TYPE, product.get().getType()));
        } else {
            badRequest(resp, "Product not found by name " + name).run();
        }
    }
}
