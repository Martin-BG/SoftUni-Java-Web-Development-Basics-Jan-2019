package metube.web.servlets.tube;

import metube.domain.models.view.TubeViewModel;
import metube.services.TubeService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@WebServlet("/tubes/details")
public class TubeDetailsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final TubeService tubeService;
    private final ModelMapper modelMapper;

    @Inject
    public TubeDetailsServlet(TubeService tubeService, ModelMapper modelMapper) {
        this.tubeService = tubeService;
        this.modelMapper = modelMapper;
    }

    private static Optional<String> getTubeName(String queryString) {
        if (queryString != null) {
            String decoded = URLDecoder.decode(queryString, StandardCharsets.UTF_8);
            String[] params = decoded.split("&");
            for (String param : params) {
                String[] kvp = param.split("=");
                if ("name".equals(kvp[0])) {
                    return Optional.ofNullable(kvp[1]);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<TubeViewModel> tube = getTubeName(req.getQueryString())
                .map(tubeService::findByName)
                .map(m -> modelMapper.map(m, TubeViewModel.class));

        if (tube.isPresent()) {
            req.setAttribute("model", tube.get());
            req.getRequestDispatcher("/WEB-INF/jsps/details-tube.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/WEB-INF/jsps/details-not-found.jsp").forward(req, resp);
        }
    }
}
