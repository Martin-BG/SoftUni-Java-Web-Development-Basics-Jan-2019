package org.softuni.exam.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "cacheFilter",
        urlPatterns = "/faces/javax.faces.resource/*")
public class CacheFilter implements Filter {

    private static final long MAX_AGE = 60 * 60 * 24 * 30L; // 30 days in seconds
    private static final String MAX_AGE_HEADER = "max-age=" + MAX_AGE + ", public";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setHeader("Cache-Control", MAX_AGE_HEADER);
        chain.doFilter(request, response);
    }
}
