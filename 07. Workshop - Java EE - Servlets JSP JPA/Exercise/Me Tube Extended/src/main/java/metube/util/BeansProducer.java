package metube.util;

import org.modelmapper.ModelMapper;

import javax.enterprise.inject.Produces;

public class BeansProducer {

    @Produces
    ModelMapper createModelMapper() {
        return new ModelMapper();
    }
}
