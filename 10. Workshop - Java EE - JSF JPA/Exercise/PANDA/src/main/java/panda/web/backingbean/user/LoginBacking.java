package panda.web.backingbean.user;

import panda.domain.model.binding.user.UserLoginBindingModel;
import panda.domain.model.view.user.UserLoggedViewModel;
import panda.service.user.UserService;
import panda.web.backingbean.BaseBackingBean;

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
                }, addMessageRunnable("Authentication failed. Invalid username or password."));
    }

    private void addUserToSession(UserLoggedViewModel user) {
        HttpSession session = request.getSession();
        session.setAttribute("userId", user.getId());
        session.setAttribute("username", user.getUsername());
    }

    public UserLoginBindingModel getModel() {
        return model;
    }
}
