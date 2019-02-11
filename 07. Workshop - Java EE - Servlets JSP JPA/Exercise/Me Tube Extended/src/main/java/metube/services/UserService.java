package metube.services;

import metube.domain.entities.User;
import metube.domain.models.binding.Bindable;
import metube.domain.models.view.Viewable;

import java.util.Optional;

public interface UserService extends Service<User, String> {

    <MODEL extends Bindable<User>> boolean register(MODEL model);

    <MODEL extends Viewable<User>> Optional<MODEL> findByUsername(String username, Class<MODEL> clazz);
}
