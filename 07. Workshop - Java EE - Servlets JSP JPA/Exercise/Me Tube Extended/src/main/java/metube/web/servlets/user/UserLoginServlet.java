package metube.web.servlets.user;

import metube.domain.models.binding.user.UserLoginBindingModel;
import metube.domain.models.view.user.UserLoggedViewModel;
import metube.services.UserService;
import metube.web.WebConstants;
import metube.web.servlets.ServletUtils;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(WebConstants.URL_USER_LOGIN)
public class UserLoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final UserService userService;

    @Inject
    public UserLoginServlet(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        ServletUtils.forward(req, resp, WebConstants.JSP_USER_LOGIN);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        UserLoginBindingModel model = (UserLoginBindingModel) req.getAttribute(WebConstants.ATTRIBUTE_MODEL);
        userService
                .login(model)
                .ifPresentOrElse(
                        (UserLoggedViewModel user) -> {
                            HttpSession session = req.getSession(true);
                            session.setAttribute(WebConstants.ATTRIBUTE_USERNAME, user.getUsername());
                            session.setAttribute(WebConstants.ATTRIBUTE_USER_ID, user.getId());
                            ServletUtils.redirect(resp, WebConstants.URL_USER_HOME);
                        },
                        () -> ServletUtils.error(resp, HttpServletResponse.SC_BAD_REQUEST, "Invalid credentials!"));
    }
}
