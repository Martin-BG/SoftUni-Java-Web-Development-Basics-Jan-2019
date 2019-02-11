package metube.web.filters;

import metube.domain.models.binding.UserLoginBindingModel;
import metube.web.WebConstants;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(WebConstants.URL_USER_LOGIN)
public class UserLoginFilter extends BaseFilter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        if (WebConstants.HTTP_METHOD_POST.equalsIgnoreCase(req.getMethod())) {
            UserLoginBindingModel model = getBindingModelFromParams(request, UserLoginBindingModel.class);
            req.setAttribute(WebConstants.ATTRIBUTE_MODEL, model);
        }

        chain.doFilter(req, response);
    }
}
