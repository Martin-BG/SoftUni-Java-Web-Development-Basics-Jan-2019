package metube.web.servlets.tube;

import metube.domain.models.view.TubeViewModel;
import metube.services.TubeService;
import metube.web.WebConstants;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Optional;

@WebServlet(WebConstants.URL_TUBES_DETAILS)
public class TubeDetailsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final TubeService tubeService;

    @Inject
    public TubeDetailsServlet(TubeService tubeService) {
        this.tubeService = tubeService;
    }

    private static Optional<String> getTubeName(String queryString) {
        if (queryString != null) {
            String decoded = URLDecoder.decode(queryString, WebConstants.SERVER_ENCODING);
            String[] params = decoded.split("&");
            for (String param : params) {
                String[] kvp = param.split("=");
                if (WebConstants.ATTRIBUTE_NAME.equals(kvp[0])) {
                    return Optional.ofNullable(kvp[1]);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getTubeName(req.getQueryString())
                .map(name -> tubeService.findByName(name, TubeViewModel.class))
                .ifPresent(tube -> req.setAttribute(WebConstants.ATTRIBUTE_MODEL, tube));

        req.getRequestDispatcher(WebConstants.JSP_TUBE_DETAILS).forward(req, resp);
    }
}
