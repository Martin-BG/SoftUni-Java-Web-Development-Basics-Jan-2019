package metube.web.servlets;

import metube.web.WebConstants;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(WebConstants.URL_INDEX_SERVLET)
public class IndexServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        ServletUtils.forward(req, resp, WebConstants.JSP_INDEX);
    }
}
