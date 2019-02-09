package metube.web.servlets.tube;

import metube.domain.models.binding.TubeCreateBindingModel;
import metube.services.TubeService;
import metube.web.WebConstants;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(WebConstants.URL_TUBES_CREATE)
public class TubeCreateServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final TubeService tubeService;

    @Inject
    public TubeCreateServlet(TubeService tubeService) {
        this.tubeService = tubeService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(WebConstants.JSP_TUBE_CREATE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        TubeCreateBindingModel model = (TubeCreateBindingModel) req.getAttribute(WebConstants.ATTRIBUTE_MODEL);
        tubeService.saveTube(model);
        resp.sendRedirect(WebConstants.URL_TUBES_DETAILS_ATR_NAME + URLEncoder.encode(model.getName(),
                WebConstants.SERVER_ENCODING));
    }
}
