package org.softuni.exam.web.beans.user;

import org.softuni.exam.domain.models.binding.user.UserRegisterBindingModel;
import org.softuni.exam.services.UserService;
import org.softuni.exam.web.beans.BaseBackingBean;

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
