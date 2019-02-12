package metube.web.filters;

import metube.web.WebConstants;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {
        WebConstants.URL_INDEX,
        WebConstants.URL_USER_REGISTER,
        WebConstants.URL_USER_LOGIN
})
public class AuthenticatedUserFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        if (req.getSession().getAttribute(WebConstants.ATTRIBUTE_USERNAME) != null) {
            resp.sendRedirect(WebConstants.URL_USER_HOME);
            return;
        }

        chain.doFilter(req, resp);
    }
}
