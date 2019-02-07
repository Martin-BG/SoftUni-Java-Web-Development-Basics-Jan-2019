package metube.web.filters;

import metube.domain.models.binding.TubeCreateBindingModel;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

@WebFilter("/tubes/create")
public class TubeCreateFilter implements Filter {
    @Inject
    private Validator validator;

    private static Object parseValue(Class<?> type, String value) {
        if (type == null || value == null) {
            return null;
        }

        if (type.isAssignableFrom(int.class) || type.isAssignableFrom(Integer.class)) {
            return Integer.valueOf(value);
        } else if (type.isAssignableFrom(long.class) || type.isAssignableFrom(Long.class)) {
            return Long.valueOf(value);
        } else if (type.isAssignableFrom(float.class) || type.isAssignableFrom(Float.class)) {
            return Float.valueOf(value);
        } else if (type.isAssignableFrom(double.class) || type.isAssignableFrom(Double.class)) {
            return Double.valueOf(value);
        } else if (type.isAssignableFrom(boolean.class) || type.isAssignableFrom(Boolean.class)) {
            return Boolean.valueOf(value);
        } else {
            return value;
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        request.setCharacterEncoding("UTF-8");
        if ("POST".equalsIgnoreCase(req.getMethod())) {
            try {
                TubeCreateBindingModel model = TubeCreateBindingModel.class.getConstructor().newInstance();
                for (Field field : TubeCreateBindingModel.class.getDeclaredFields()) {
                    field.setAccessible(true);
                    String value = request.getParameter(field.getName());
                    if (value != null) {
                        String parameterStr = value.trim();
                        Object parameterValue = parseValue(field.getType(), parameterStr);
                        field.set(model, parameterValue);
                    }
                }
                Set<ConstraintViolation<TubeCreateBindingModel>> violations = validator.validate(model);
                if (violations.isEmpty()) {
                    req.setAttribute("model", model);
                } else {
                    req.setAttribute("violations", violations);
                }
            } catch (IllegalAccessException | InstantiationException |
                    NoSuchMethodException | InvocationTargetException e) {
            }
        }
//            TubeCreateBindingModel model = new TubeCreateBindingModel();
//            model.setName(req.getParameter("name"));
//            model.setDescription(req.getParameter("description"));
//            model.setUploader(req.getParameter("uploader"));
//            model.setYouTubeLink(req.getParameter("youTubeLink"));

//        req.setAttribute("tubeCreateBindingModel", model);

        chain.doFilter(req, resp);

    }
}
