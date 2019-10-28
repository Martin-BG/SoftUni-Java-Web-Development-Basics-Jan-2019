package panda.service.user;

import panda.domain.api.Viewable;
import panda.domain.entity.User;
import panda.domain.model.binding.user.UserLoginBindingModel;
import panda.domain.model.binding.user.UserRegisterBindingModel;
import panda.domain.model.view.user.UserLoggedViewModel;
import panda.service.Service;

import java.util.Optional;
import java.util.UUID;

public interface UserService extends Service<User, UUID> {

    boolean register(UserRegisterBindingModel model);

    Optional<UserLoggedViewModel> login(UserLoginBindingModel model);

    <M extends Viewable<User>> Optional<M> findByUsername(String username, Class<M> clazz);
}
