package metube.web.filters;

import metube.domain.models.binding.TubeUploadBindingModel;
import metube.domain.models.binding.UserIdBindingModel;
import metube.web.WebConstants;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(WebConstants.URL_TUBE_UPLOAD)
public class TubeUploadFilter extends BaseFilter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        if (WebConstants.HTTP_METHOD_POST.equalsIgnoreCase(req.getMethod())) {
            TubeUploadBindingModel model = getBindingModelFromParams(request, TubeUploadBindingModel.class);
            model.setUploader(new UserIdBindingModel());
            String userId = (String) req.getSession().getAttribute(WebConstants.ATTRIBUTE_USER_ID);
            model.getUploader().setId(userId);
            req.setAttribute(WebConstants.ATTRIBUTE_MODEL, model);
        }

        chain.doFilter(req, response);
    }
}
