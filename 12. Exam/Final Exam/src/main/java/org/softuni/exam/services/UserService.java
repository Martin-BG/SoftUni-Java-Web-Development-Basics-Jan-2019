package org.softuni.exam.services;

import org.softuni.exam.domain.entities.User;
import org.softuni.exam.domain.models.binding.user.UserLoginBindingModel;
import org.softuni.exam.domain.models.binding.user.UserRegisterBindingModel;
import org.softuni.exam.domain.models.view.Viewable;
import org.softuni.exam.domain.models.view.user.UserLoggedViewModel;

import java.util.Optional;

public interface UserService extends Service<User, String> {

    boolean register(UserRegisterBindingModel model);

    Optional<UserLoggedViewModel> login(UserLoginBindingModel model);

    <M extends Viewable<User>> Optional<M> findByUsername(String username, Class<M> clazz);
}
