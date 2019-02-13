package metube.web.servlets.user;

import metube.web.WebConstants;
import metube.web.servlets.ServletUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(WebConstants.URL_USER_LOGOUT)
public class UserLogoutServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession(true).invalidate();

        ServletUtil.redirect(resp, WebConstants.URL_INDEX);
    }
}
