package metube.util;

import org.modelmapper.ModelMapper;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.util.logging.Logger;

public class BeansProducer {

    @Produces
    Logger createLogger(InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }

    @Produces
    ModelMapper createModelMapper() {
        return new ModelMapper();
    }
}
