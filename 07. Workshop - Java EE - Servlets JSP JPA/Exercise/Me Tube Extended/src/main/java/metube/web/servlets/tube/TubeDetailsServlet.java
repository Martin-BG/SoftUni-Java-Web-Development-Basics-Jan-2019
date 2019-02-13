package metube.web.servlets.tube;

import metube.domain.models.view.tube.TubeDetailsViewModel;
import metube.services.TubeService;
import metube.web.WebConstants;
import metube.web.servlets.ServletUtil;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(WebConstants.URL_TUBE_DETAILS + "/*")
public class TubeDetailsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final TubeService tubeService;

    @Inject
    public TubeDetailsServlet(TubeService tubeService) {
        this.tubeService = tubeService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String tubeId = req.getRequestURI().substring(req.getRequestURI().lastIndexOf('/') + 1);
        tubeService
                .view(tubeId, TubeDetailsViewModel.class)
                .ifPresentOrElse(
                        tube -> {
                            req.setAttribute(WebConstants.ATTRIBUTE_MODEL, tube);
                            ServletUtil.forward(req, resp, WebConstants.JSP_TUBE_DETAILS);
                        },
                        () -> ServletUtil.error(resp, HttpServletResponse.SC_NOT_FOUND, "Tube not found: " + tubeId));
    }
}
