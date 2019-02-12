package metube.web.servlets.user;

import metube.domain.models.binding.user.UserLoginBindingModel;
import metube.domain.models.view.user.UserLoggedViewModel;
import metube.services.UserService;
import metube.web.WebConstants;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet(WebConstants.URL_USER_LOGIN)
public class UserLoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final UserService userService;

    @Inject
    public UserLoginServlet(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(WebConstants.JSP_USER_LOGIN).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserLoginBindingModel model = (UserLoginBindingModel) req.getAttribute(WebConstants.ATTRIBUTE_MODEL);
        Optional<UserLoggedViewModel> user = userService.login(model);
        if (user.isPresent()) {
            HttpSession session = req.getSession(true);
            session.setAttribute(WebConstants.ATTRIBUTE_USERNAME, user.get().getUsername());
            session.setAttribute(WebConstants.ATTRIBUTE_USER_ID, user.get().getId());
            resp.sendRedirect(WebConstants.URL_USER_HOME);
        } else {
            resp.sendRedirect(WebConstants.URL_USER_LOGIN);
        }
    }
}
