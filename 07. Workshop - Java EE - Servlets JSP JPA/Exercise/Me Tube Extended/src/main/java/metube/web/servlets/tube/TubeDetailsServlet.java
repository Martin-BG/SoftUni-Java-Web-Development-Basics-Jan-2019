package metube.web.servlets.tube;

import metube.domain.models.view.tube.TubeDetailsViewModel;
import metube.services.TubeService;
import metube.web.WebConstants;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(WebConstants.URL_TUBE_DETAILS + "/*")
public class TubeDetailsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final TubeService tubeService;

    @Inject
    public TubeDetailsServlet(TubeService tubeService) {
        this.tubeService = tubeService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tubeId = req.getRequestURI().substring(req.getRequestURI().lastIndexOf('/') + 1);
        Optional<TubeDetailsViewModel> tube = tubeService.view(tubeId, TubeDetailsViewModel.class);

        if (tube.isPresent()) {
            req.setAttribute(WebConstants.ATTRIBUTE_MODEL, tube.get());
            req.getRequestDispatcher(WebConstants.JSP_TUBE_DETAILS).forward(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Tube not found: " + tubeId);
        }
    }
}

