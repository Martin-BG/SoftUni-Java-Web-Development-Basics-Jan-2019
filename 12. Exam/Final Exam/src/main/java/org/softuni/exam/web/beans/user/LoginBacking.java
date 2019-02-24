package org.softuni.exam.web.beans.user;

import org.softuni.exam.domain.models.binding.user.UserLoginBindingModel;
import org.softuni.exam.domain.models.view.user.UserLoggedViewModel;
import org.softuni.exam.services.UserService;
import org.softuni.exam.web.beans.BaseBackingBean;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Model
public class LoginBacking extends BaseBackingBean {

    @Inject
    private UserService service;

    @Inject
    private HttpServletRequest request;

    private UserLoginBindingModel model = new UserLoginBindingModel();

    public void login() {
        service.login(model)
                .ifPresentOrElse(user -> {
                    addUserToSession(user);
                    redirect("/home");
                }, addMessageRunnable("Authentication Failed. Check username or password."));
    }

    public UserLoginBindingModel getModel() {
        return model;
    }

    private void addUserToSession(UserLoggedViewModel user) {
        HttpSession session = request.getSession();
        session.setAttribute("userId", user.getId());
        session.setAttribute("username", user.getUsername());
    }
}
