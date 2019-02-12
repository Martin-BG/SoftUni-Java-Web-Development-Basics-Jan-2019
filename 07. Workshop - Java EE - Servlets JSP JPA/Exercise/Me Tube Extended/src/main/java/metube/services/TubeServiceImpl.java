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

    private static final Logger LOG = Logger.getLogger(TubeServiceImpl.class.getName());

    @Inject
    public TubeServiceImpl(TubeRepository repository,
                           ModelMapper mapper,
                           Validator validator) {
        super(mapper, validator, repository);
    }

    @Override
    protected Logger logger() {
        return LOG;
    }

    @Override
    public <M extends Bindable<Tube>> boolean upload(M model) {
        return create(model);
    }

    @Override
    public <M extends Viewable<Tube>> Optional<M> findByName(String name, Class<M> clazz) {
        return repository
                .findByName(name)
                .map(e -> mapper.map(e, clazz));
    }

    @Override
    public <M extends Viewable<Tube>> Optional<M> view(String id, Class<M> clazz) {
        Optional<Tube> tube = repository.read(id);
        tube.ifPresent(t -> {
            t.setViews(t.getViews() + 1);
            repository.update(t);
        });
        return tube.map(t -> mapper.map(t, clazz));
    }
}
