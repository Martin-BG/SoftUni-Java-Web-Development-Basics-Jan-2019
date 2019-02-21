package fdmcjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;
import java.util.logging.Level;
import java.util.logging.Logger;

@FacesConverter("dateConverter")
public class DateConverter extends DateTimeConverter {

    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final String LOCAL_DATE_TYPE = "localDate";

    private static final Logger LOG = Logger.getLogger(DateConverter.class.getName());

    public DateConverter() {
        super.setPattern(DATE_PATTERN);
        super.setType(LOCAL_DATE_TYPE);
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            return super.getAsObject(context, component, value);
        } catch (ConverterException e) {
            LOG.log(Level.SEVERE, "Invalid date: " + value, e);
            return null;
        }
    }
}
