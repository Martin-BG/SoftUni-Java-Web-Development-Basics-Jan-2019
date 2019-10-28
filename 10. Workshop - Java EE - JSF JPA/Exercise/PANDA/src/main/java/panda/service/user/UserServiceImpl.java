package panda.service.user;

import org.modelmapper.ModelMapper;
import panda.domain.api.Viewable;
import panda.domain.entity.User;
import panda.domain.entity.role.Role;
import panda.domain.model.binding.user.UserLoginBindingModel;
import panda.domain.model.binding.user.UserRegisterBindingModel;
import panda.domain.model.view.user.UserLoggedViewModel;
import panda.repository.user.UserRepository;
import panda.service.BaseService;
import panda.service.provider.PasswordHasherProvider;

import javax.inject.Inject;
import javax.validation.Validator;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

public class UserServiceImpl extends BaseService<User, UUID, UserRepository> implements UserService {

    private static final Logger LOG = Logger.getLogger(UserServiceImpl.class.getName());

    private final PasswordHasherProvider passwordHasher;

    @Inject
    public UserServiceImpl(UserRepository repository,
                           ModelMapper mapper,
                           Validator validator,
                           PasswordHasherProvider passwordHasher) {
        super(mapper, validator, repository);
        this.passwordHasher = passwordHasher;
    }

    @Override
    protected Logger logger() {
        return LOG;
    }

    @Override
    public boolean register(UserRegisterBindingModel model) {
        if (model == null || !model.getPassword().equals(model.getConfirmPassword())) {
            return false;
        }

        String encodedPassword = passwordHasher.encodedHash(model.getPassword().toCharArray());
        model.setPassword(encodedPassword);

        if (repository.isEmpty()) {
            model.setRole(Role.ADMIN);
        } else {
            model.setRole(Role.USER);
        }

        return create(model);
    }

    @Override
    public Optional<UserLoggedViewModel> login(UserLoginBindingModel model) {
        if (model == null || !validator.validate(model).isEmpty()) {
            return Optional.empty();
        }

        return repository
                .findByUsername(model.getUsername())
                .filter(u -> passwordHasher.verifyEncoded(u.getPassword(), model.getPassword().toCharArray()))
                .map(u -> mapper.map(u, UserLoggedViewModel.class));
    }

    @Override
    public <M extends Viewable<User>> Optional<M> findByUsername(String username, Class<M> clazz) {
        return repository
                .findByUsername(username)
                .map(e -> mapper.map(e, clazz));
    }
}
