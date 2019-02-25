package org.softuni.exam.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "authenticatedUserFilter",
        urlPatterns = {"/", "/login", "/register"})
public class AuthenticatedUserFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletResponse res = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;

        if (req.getSession().getAttribute("username") != null) {
            res.sendRedirect("/home");
            return;
        }

        chain.doFilter(request, response);
    }
}
