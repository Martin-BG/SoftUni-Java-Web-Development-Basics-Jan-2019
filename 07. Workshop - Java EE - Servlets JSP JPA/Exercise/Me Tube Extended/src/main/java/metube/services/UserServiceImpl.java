package metube.services;

import metube.domain.entities.User;
import metube.domain.models.binding.Bindable;
import metube.domain.models.view.Viewable;
import metube.repositories.UserRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.validation.Validator;
import java.util.Optional;
import java.util.logging.Logger;

public class UserServiceImpl extends BaseService<User, String, UserRepository> implements UserService {

    @Inject
    public UserServiceImpl(Logger logger,
                           UserRepository repository,
                           ModelMapper mapper,
                           Validator validator) {
        super(mapper, validator, logger, repository);
    }

    @Override
    public <MODEL extends Bindable<User>> boolean register(MODEL model) {
        return create(model);
    }

    @Override
    public <MODEL extends Viewable<User>> Optional<MODEL> findByUsername(String username, Class<MODEL> clazz) {
        return repository
                .findByUsername(username)
                .map(e -> mapper.map(e, clazz));
    }
}
