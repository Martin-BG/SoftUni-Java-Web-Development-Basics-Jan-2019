package panda.config;

import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.faces.annotation.FacesConfig;
import java.util.TimeZone;
import java.util.logging.Logger;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@ApplicationScoped
public class ApplicationConfig {

    /**
     * Configure ModelMapper to use field instead of property access for mapping between classes
     * and instances thus promoting better encapsulation and immutability.
     * <p>
     * Expect a single "Illegal reflective access by org.modelmapper.internal.PropertyInfoImpl$FieldPropertyInfo..."
     * warning in logs on first usage because of a ModelMapper
     * <a href="https://github.com/modelmapper/modelmapper/issues/414#issuecomment-469730463">issue</a>
     *
     * @return ModelMapper bean
     */
    @Produces
    @ApplicationScoped
    ModelMapper createModelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setFieldMatchingEnabled(true);

        return modelMapper;
    }

    @Produces
    @Dependent
    public Logger getLogger(InjectionPoint p) {
        return Logger.getLogger(p.getMember().getDeclaringClass().getName());
    }

    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }
}
