package metube.web.filters;

import metube.domain.models.binding.Bindable;
import metube.web.WebConstants;

import javax.servlet.Filter;
import javax.servlet.ServletRequest;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.util.Optional;

abstract class BaseFilter implements Filter {

    private static Optional<String> getQueryParam(String queryString, String paramName) {
        if (queryString != null && paramName != null) {
            String decoded = URLDecoder.decode(queryString, WebConstants.SERVER_ENCODING);
            String[] params = decoded.split("&");
            for (String param : params) {
                String[] kvp = param.split("=");
                if (paramName.equals(kvp[0])) {
                    return Optional.ofNullable(kvp[1]);
                }
            }
        }
        return Optional.empty();
    }

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

    protected static <T extends Bindable> T getBindingModelFromParams(ServletRequest request, Class<T> clazz) {
        try {
            request.setCharacterEncoding(WebConstants.SERVER_ENCODING_STR);
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
                NoSuchMethodException | InvocationTargetException |
                UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
