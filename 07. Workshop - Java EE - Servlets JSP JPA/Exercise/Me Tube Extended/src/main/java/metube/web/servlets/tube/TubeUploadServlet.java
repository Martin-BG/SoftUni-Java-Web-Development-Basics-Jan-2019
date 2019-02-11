package metube.web.servlets.tube;

import metube.domain.models.binding.TubeUploadBindingModel;
import metube.services.TubeService;
import metube.web.WebConstants;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(WebConstants.URL_TUBE_UPLOAD)
public class TubeUploadServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final TubeService tubeService;

    @Inject
    public TubeUploadServlet(TubeService tubeService) {
        this.tubeService = tubeService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(WebConstants.JSP_TUBE_UPLOAD).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TubeUploadBindingModel model = (TubeUploadBindingModel) req.getAttribute(WebConstants.ATTRIBUTE_MODEL);
        if (tubeService.upload(model)) {
            resp.sendRedirect(WebConstants.URL_USER_HOME);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Tube upload failed");
        }
    }
}
