package metube.web.filters;

import metube.domain.models.binding.tube.TubeUploadBindingModel;
import metube.domain.models.binding.user.UserIdBindingModel;
import metube.web.WebConstants;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(WebConstants.URL_TUBE_UPLOAD)
public class TubeUploadFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        if (WebConstants.HTTP_METHOD_POST.equalsIgnoreCase(req.getMethod())) {
            TubeUploadBindingModel model = FilterUtils
                    .getBindingModelFromParams(request, TubeUploadBindingModel.class);
            model.setUploader(new UserIdBindingModel());
            String userId = (String) req.getSession().getAttribute(WebConstants.ATTRIBUTE_USER_ID);
            model.getUploader().setId(userId);
            req.setAttribute(WebConstants.ATTRIBUTE_MODEL, model);
        }

        chain.doFilter(req, response);
    }
}
