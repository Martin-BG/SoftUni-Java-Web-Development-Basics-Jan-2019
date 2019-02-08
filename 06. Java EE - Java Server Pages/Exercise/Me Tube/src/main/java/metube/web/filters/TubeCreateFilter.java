package metube.web.filters;

import metube.domain.models.binding.Bindable;
import metube.domain.models.binding.TubeCreateBindingModel;
import metube.web.WebConstants;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;

@WebFilter(WebConstants.URL_TUBES_CREATE)
public class TubeCreateFilter implements Filter {

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

    private static <T extends Bindable> T getBindingModelFromParams(ServletRequest request, Class<T> clazz) {
        try {
            T model = clazz.getConstructor().newInstance();
            for (Field field : clazz.getDeclaredFields()) {
                String value = request.getParameter(field.getName());
                if (value != null) {
                    String parameterStr = value.trim();
                    Object parameterValue = parseValue(field.getType(), parameterStr);
                    field.setAccessible(true);
                    field.set(model, parameterValue);
                }
            }
            return model;
        } catch (IllegalAccessException | InstantiationException |
                NoSuchMethodException | InvocationTargetException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        if (WebConstants.HTTP_METHOD_POST.equalsIgnoreCase(req.getMethod())) {
            request.setCharacterEncoding(WebConstants.SERVER_ENCODING_STR);
            String serverEncodingStr = WebConstants.SERVER_ENCODING_STR;
            Charset serverEncoding = WebConstants.SERVER_ENCODING;

            TubeCreateBindingModel model = getBindingModelFromParams(request, TubeCreateBindingModel.class);
            req.setAttribute(WebConstants.ATTRIBUTE_MODEL, model);
        }

        chain.doFilter(req, response);
    }
}
