package metube.web.servlets.tube;

import metube.domain.models.view.TubeNameViewModel;
import metube.services.TubeService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/tubes/all")
public class TubeAllServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final TubeService tubeService;
    private final ModelMapper modelMapper;

    @Inject
    public TubeAllServlet(TubeService tubeService, ModelMapper modelMapper) {
        this.tubeService = tubeService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TubeNameViewModel> tubes = tubeService
                .findAll().stream()
                .map(t -> modelMapper.map(t, TubeNameViewModel.class))
                .collect(Collectors.toList());
        req.setAttribute("tubes", tubes);
        req.getRequestDispatcher("/WEB-INF/jsps/all-tubes.jsp").forward(req, resp);
    }
}
