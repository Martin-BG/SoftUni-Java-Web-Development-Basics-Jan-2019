package metube.services;

import metube.domain.entities.User;
import metube.domain.models.binding.user.UserLoginBindingModel;
import metube.domain.models.binding.user.UserRegisterBindingModel;
import metube.domain.models.view.Viewable;
import metube.domain.models.view.user.UserLoggedViewModel;

import java.util.Optional;

public interface UserService extends Service<User, String> {

    boolean register(UserRegisterBindingModel model);

    Optional<UserLoggedViewModel> login(UserLoginBindingModel model);

    <MODEL extends Viewable<User>> Optional<MODEL> findByUsername(String username, Class<MODEL> clazz);
}
