package sbojbg.services;

import sbojbg.domain.entities.User;
import sbojbg.domain.models.binding.user.UserLoginBindingModel;
import sbojbg.domain.models.binding.user.UserRegisterBindingModel;
import sbojbg.domain.models.view.Viewable;
import sbojbg.domain.models.view.user.UserLoggedViewModel;

import java.util.Optional;

public interface UserService extends Service<User, String> {

    boolean register(UserRegisterBindingModel model);

    Optional<UserLoggedViewModel> login(UserLoginBindingModel model);

    <M extends Viewable<User>> Optional<M> findByUsername(String username, Class<M> clazz);
}
