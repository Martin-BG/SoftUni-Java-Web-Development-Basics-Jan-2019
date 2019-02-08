package metube.services;

import metube.domain.entities.Tube;
import metube.domain.models.binding.Bindable;
import metube.domain.models.view.Viewable;
import metube.repository.TubeRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class TubeServiceImpl implements TubeService {

    private final Logger logger;
    private final TubeRepository tubeRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;

    @Inject
    public TubeServiceImpl(Logger logger,
                           TubeRepository tubeRepository,
                           ModelMapper modelMapper,
                           Validator validator) {
        this.logger = logger;
        this.tubeRepository = tubeRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }

    @Override
    public <T extends Bindable> void saveTube(T model) {
        validateModel(model);
        tubeRepository.save(modelMapper.map(model, Tube.class));
    }

    @Override
    public <T extends Viewable> T findByName(String name, Class<T> clazz) {
        return tubeRepository
                .findByName(name)
                .map(tube -> modelMapper.map(tube, clazz))
                .orElse(null);
    }

    @Override
    public <T extends Viewable> List<T> findAll(Class<T> clazz) {
        return tubeRepository
                .findAll()
                .stream()
                .map(t -> modelMapper.map(t, clazz))
                .collect(Collectors.toList());
    }

    private <T extends Bindable> void validateModel(T model) {
        Set<ConstraintViolation<T>> violations = validator.validate(model);
        if (!violations.isEmpty()) {
            String msg = "Failed validation on:\n\t" +
                    violations.stream()
                            .map(cv -> cv.getPropertyPath().toString()
                                    + " (" + cv.getInvalidValue() + ") " + cv.getMessage())
                            .collect(Collectors.joining("\r\n\t"));
            logger.log(Level.WARNING, msg);
            throw new IllegalArgumentException(msg);
        }
    }
}
