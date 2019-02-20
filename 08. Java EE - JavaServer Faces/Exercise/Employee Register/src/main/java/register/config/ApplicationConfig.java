package register.config;

import org.modelmapper.ModelMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.faces.annotation.FacesConfig;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@ApplicationScoped
public class ApplicationConfig {

    @Produces
    @ApplicationScoped
    ModelMapper createModelMapper() {
        return new ModelMapper();
    }
}
