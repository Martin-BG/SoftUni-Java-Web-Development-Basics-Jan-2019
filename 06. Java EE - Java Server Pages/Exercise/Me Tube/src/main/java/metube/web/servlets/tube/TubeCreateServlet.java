package metube.web.servlets.tube;

import metube.domain.models.binding.TubeCreateBindingModel;
import metube.domain.models.service.TubeServiceModel;
import metube.services.TubeService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet("/tubes/create")
public class TubeCreateServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final TubeService tubeService;
    private final ModelMapper modelMapper;

    @Inject
    public TubeCreateServlet(TubeService tubeService, ModelMapper modelMapper) {
        this.tubeService = tubeService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsps/create-tube.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TubeCreateBindingModel model = (TubeCreateBindingModel) req.getAttribute("model");

        tubeService.saveTube(modelMapper.map(model, TubeServiceModel.class));

        resp.sendRedirect("/tubes/details?name=" + URLEncoder.encode(model.getName(), StandardCharsets.UTF_8));
    }
}
