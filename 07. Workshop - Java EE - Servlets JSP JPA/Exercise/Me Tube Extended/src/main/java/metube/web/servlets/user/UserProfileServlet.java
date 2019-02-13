package metube.web.servlets.user;

import metube.domain.models.view.user.UserProfileViewModel;
import metube.services.UserService;
import metube.web.WebConstants;
import metube.web.servlets.ServletUtils;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(WebConstants.URL_USER_PROFILE)
public class UserProfileServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final UserService userService;

    @Inject
    public UserProfileServlet(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String username = (String) req.getSession().getAttribute(WebConstants.ATTRIBUTE_USERNAME);

        userService
                .findByUsername(username, UserProfileViewModel.class)
                .ifPresentOrElse(
                        user -> {
                            req.setAttribute(WebConstants.ATTRIBUTE_MODEL, user);
                            ServletUtils.forward(req, resp, WebConstants.JSP_USER_PROFILE);
                        },
                        () -> ServletUtils.error(resp, HttpServletResponse.SC_UNAUTHORIZED, "Please login first!"));
    }
}
