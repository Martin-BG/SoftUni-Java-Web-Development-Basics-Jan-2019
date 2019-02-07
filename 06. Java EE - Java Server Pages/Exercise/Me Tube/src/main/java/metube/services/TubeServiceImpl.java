package metube.services;

import metube.domain.entities.Tube;
import metube.domain.models.service.TubeServiceModel;
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
    public void saveTube(TubeServiceModel tubeServiceModel) {
        validateModel(tubeServiceModel);

        tubeRepository.save(modelMapper.map(tubeServiceModel, Tube.class));
    }

    @Override
    public TubeServiceModel findByName(String name) {
        return tubeRepository
                .findByName(name)
                .map(tube -> modelMapper.map(tube, TubeServiceModel.class))
                .orElse(null);
    }

    @Override
    public List<TubeServiceModel> findAll() {
        return tubeRepository
                .findAll()
                .stream()
                .map(t -> modelMapper.map(t, TubeServiceModel.class))
                .collect(Collectors.toList());
    }

    private <T> void validateModel(T model) {
        Set<ConstraintViolation<T>> violations = validator.validate(model);
        if (!violations.isEmpty()) {
            String msg = "Failed validation on:\n\t" +
                    violations.stream()
                            .map(ConstraintViolation::getMessage)
                            .collect(Collectors.joining("\n\t"));
            logger.log(Level.WARNING, msg);
            throw new IllegalArgumentException(msg);
        }
    }
}
