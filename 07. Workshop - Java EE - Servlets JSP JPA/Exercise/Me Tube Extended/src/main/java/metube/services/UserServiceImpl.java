package metube.services;

import metube.domain.entities.User;
import metube.domain.models.binding.UserLoginBindingModel;
import metube.domain.models.binding.UserRegisterBindingModel;
import metube.domain.models.view.Viewable;
import metube.domain.models.view.user.UserLoggedViewModel;
import metube.repositories.UserRepository;
import metube.util.PasswordHasher;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.validation.Validator;
import java.util.Optional;
import java.util.logging.Logger;

public class UserServiceImpl extends BaseService<User, String, UserRepository> implements UserService {

    private final PasswordHasher passwordHasher;

    @Inject
    public UserServiceImpl(Logger logger,
                           UserRepository repository,
                           ModelMapper mapper,
                           Validator validator,
                           PasswordHasher passwordHasher) {
        super(mapper, validator, logger, repository);
        this.passwordHasher = passwordHasher;
    }

    @Override
    public boolean register(UserRegisterBindingModel model) {
        if (model == null || !model.getPassword().equals(model.getConfirmPassword())) {
            return false;
        }

        String encodedPassword = passwordHasher.encodedHash(model.getPassword().toCharArray());
        model.setPassword(encodedPassword);

        return create(model);
    }

    @Override
    public Optional<UserLoggedViewModel> login(UserLoginBindingModel model) {
        if (model == null || !validator.validate(model).isEmpty()) {
            return Optional.empty();
        }
        return repository
                .findByUsername(model.getUsername(), false)
                .filter(u -> passwordHasher.verifyEncoded(u.getPassword(), model.getPassword().toCharArray()))
                .map(u -> mapper.map(u, UserLoggedViewModel.class));
    }

    @Override
    public <MODEL extends Viewable<User>> Optional<MODEL> findByUsername(String username, Class<MODEL> clazz) {
        return repository
                .findByUsername(username, true)
                .map(e -> mapper.map(e, clazz));
    }
}
