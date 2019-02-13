package metube.web.servlets.user;

import metube.domain.models.view.tube.TubeThumbnailViewModel;
import metube.services.TubeService;
import metube.web.WebConstants;
import metube.web.servlets.ServletUtils;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(WebConstants.URL_USER_HOME)
public class UserHomeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final TubeService tubeService;

    @Inject
    public UserHomeServlet(TubeService tubeService) {
        this.tubeService = tubeService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        List<TubeThumbnailViewModel> tubes = tubeService
                .findAll(TubeThumbnailViewModel.class);

        req.setAttribute(WebConstants.ATTRIBUTE_MODEL, tubes);

        ServletUtils.forward(req, resp, WebConstants.JSP_USER_HOME);
    }
}
