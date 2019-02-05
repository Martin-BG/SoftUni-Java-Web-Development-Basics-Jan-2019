package chushka.web.servlets;

import chushka.service.ProductService;
import chushka.utils.htmlbuilder.HtmlBuilder;
import chushka.utils.reader.HtmlFileReader;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@WebServlet("/products/all")
public class ProductAllServlet extends BaseServlet {

    private static final long serialVersionUID = 1L;

    private static final String URI_PRODUCT_LIST_HTML = "/html/templates/product/list-item.html";
    private static final String URI_ALL_PRODUCTS_HTML = "/html/templates/product/all.html";
    private static final String HTML_PRODUCTS_PLACEHOLDER = "products";

    private final ProductService productService;
    private final HtmlFileReader htmlFileReader;

    @Inject
    public ProductAllServlet(ProductService productService,
                             HtmlFileReader htmlFileReader,
                             HtmlBuilder htmlBuilder) {
        super(htmlBuilder);
        this.productService = productService;
        this.htmlFileReader = htmlFileReader;
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        htmlFileReader
                .read(URI_PRODUCT_LIST_HTML)
                .ifPresentOrElse(html -> {
                    String itemsList = getProductsHtmlList(html, productService.findAllProducts());
                    handleResponse(resp,
                            Map.of(HTML_SKELETON_BODY_PLACEHOLDER, URI_ALL_PRODUCTS_HTML),
                            Map.of(HTML_PRODUCTS_PLACEHOLDER, itemsList));
                }, notFound(resp, URI_PRODUCT_LIST_HTML));
    }
}
