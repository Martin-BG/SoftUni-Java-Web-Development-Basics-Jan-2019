package metube.web.filters;

import metube.web.WebConstants;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {
        WebConstants.URL_USER_HOME,
        WebConstants.URL_USER_PROFILE,
        WebConstants.URL_USER_LOGOUT,
        WebConstants.URL_TUBE_BASE + "/*"
})
public class GuestUserFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        if (req.getSession().getAttribute(WebConstants.ATTRIBUTE_USERNAME) == null) {
            resp.sendRedirect(WebConstants.URL_USER_LOGIN);
            return;
        }

        chain.doFilter(req, resp);
    }
}
