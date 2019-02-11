package metube.web.servlets.user;

import metube.domain.models.view.user.UserProfileViewModel;
import metube.services.UserService;
import metube.web.WebConstants;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(WebConstants.URL_USER_PROFILE)
public class UserProfileServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final UserService userService;

    @Inject
    public UserProfileServlet(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String[] path = {WebConstants.JSP_USER_LOGIN};

        String loggedUserId = (String) req.getSession().getAttribute(WebConstants.ATTRIBUTE_USERNAME);
        if (loggedUserId != null) {
            userService
                    .findByUsername(loggedUserId, UserProfileViewModel.class)
                    .ifPresent(u -> {
                        req.setAttribute(WebConstants.ATTRIBUTE_MODEL, u);
                        path[0] = WebConstants.JSP_USER_PROFILE;
                    });
        }

        req.getRequestDispatcher(path[0]).forward(req, resp);
    }
}
