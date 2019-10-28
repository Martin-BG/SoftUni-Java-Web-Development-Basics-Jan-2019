package panda.web.backingbean.user;

import panda.domain.model.binding.user.UserRegisterBindingModel;
import panda.service.user.UserService;
import panda.web.backingbean.BaseBackingBean;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class RegisterBacking extends BaseBackingBean {

    @Inject
    private UserService service;

    private UserRegisterBindingModel model = new UserRegisterBindingModel();

    public void register() {
        if (service.register(model)) {
            redirect("/login");
        } else {
            addMessage("Registration failed. Please try again or contact support.");
        }
    }

    public UserRegisterBindingModel getModel() {
        return model;
    }
}
