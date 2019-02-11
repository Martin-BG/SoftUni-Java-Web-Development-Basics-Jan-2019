package metube.web.filters;

import metube.domain.models.binding.UserRegisterBindingModel;
import metube.web.WebConstants;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(WebConstants.URL_USER_REGISTER)
public class UserRegisterFilter extends BaseFilter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        if (WebConstants.HTTP_METHOD_POST.equalsIgnoreCase(req.getMethod())) {
            UserRegisterBindingModel model = getBindingModelFromParams(request, UserRegisterBindingModel.class);
            req.setAttribute(WebConstants.ATTRIBUTE_MODEL, model);
        }

        chain.doFilter(req, response);
    }
}
