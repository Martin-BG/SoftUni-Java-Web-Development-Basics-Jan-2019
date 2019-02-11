package metube.services;

import metube.domain.entities.Tube;
import metube.domain.models.binding.Bindable;
import metube.domain.models.view.Viewable;
import metube.repositories.TubeRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.validation.Validator;
import java.util.Optional;
import java.util.logging.Logger;

public class TubeServiceImpl extends BaseService<Tube, String, TubeRepository> implements TubeService {

    @Inject
    public TubeServiceImpl(Logger logger,
                           TubeRepository repository,
                           ModelMapper mapper,
                           Validator validator) {
        super(mapper, validator, logger, repository);
    }

    @Override
    public <MODEL extends Bindable<Tube>> boolean upload(MODEL model) {
        return create(model);
    }

    @Override
    public <MODEL extends Viewable<Tube>> Optional<MODEL> findByName(String name, Class<MODEL> clazz) {
        return repository
                .findByName(name)
                .map(e -> mapper.map(e, clazz));
    }
}
