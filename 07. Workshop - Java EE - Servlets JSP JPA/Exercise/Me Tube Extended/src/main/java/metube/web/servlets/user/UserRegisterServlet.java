package metube.web.servlets.user;

import metube.domain.models.binding.user.UserRegisterBindingModel;
import metube.services.UserService;
import metube.web.WebConstants;
import metube.web.servlets.ServletUtils;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(WebConstants.URL_USER_REGISTER)
public class UserRegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final UserService userService;

    @Inject
    public UserRegisterServlet(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        ServletUtils.forward(req, resp, WebConstants.JSP_USER_REGISTER);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        UserRegisterBindingModel model = (UserRegisterBindingModel) req.getAttribute(WebConstants.ATTRIBUTE_MODEL);

        if (userService.register(model)) {
            ServletUtils.redirect(resp, WebConstants.URL_USER_LOGIN);
        } else {
            ServletUtils.error(resp, HttpServletResponse.SC_BAD_REQUEST, "User registration failed");
        }
    }
}
