package metube.web.servlets.tube;

import metube.domain.models.binding.tube.TubeUploadBindingModel;
import metube.services.TubeService;
import metube.web.WebConstants;
import metube.web.servlets.ServletUtils;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(WebConstants.URL_TUBE_UPLOAD)
public class TubeUploadServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final TubeService tubeService;

    @Inject
    public TubeUploadServlet(TubeService tubeService) {
        this.tubeService = tubeService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        ServletUtils.forward(req, resp, WebConstants.JSP_TUBE_UPLOAD);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        TubeUploadBindingModel model = (TubeUploadBindingModel) req.getAttribute(WebConstants.ATTRIBUTE_MODEL);

        if (tubeService.upload(model)) {
            ServletUtils.redirect(resp, WebConstants.URL_USER_HOME);
        } else {
            ServletUtils.error(resp, HttpServletResponse.SC_BAD_REQUEST, "Tube upload failed");
        }
    }
}
