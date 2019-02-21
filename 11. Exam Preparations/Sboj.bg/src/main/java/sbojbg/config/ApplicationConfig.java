package sbojbg.config;

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

    @Produces
    @ApplicationScoped
    ModelMapper createModelMapper() {
        return new ModelMapper();
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
