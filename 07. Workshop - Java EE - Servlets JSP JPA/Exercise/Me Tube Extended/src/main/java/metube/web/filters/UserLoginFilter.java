package metube.web.filters;

import metube.domain.models.binding.UserLoginBindingModel;
import metube.web.WebConstants;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(WebConstants.URL_USER_LOGIN)
public class UserLoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        if (WebConstants.HTTP_METHOD_POST.equalsIgnoreCase(req.getMethod())) {
            UserLoginBindingModel model = FilterUtils
                    .getBindingModelFromParams(request, UserLoginBindingModel.class);
            req.setAttribute(WebConstants.ATTRIBUTE_MODEL, model);
        }

        chain.doFilter(req, response);
    }
}
