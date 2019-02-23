package sbojbg.web.beans.user;

import sbojbg.domain.models.binding.user.UserRegisterBindingModel;
import sbojbg.services.UserService;
import sbojbg.web.beans.BaseBackingBean;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class RegisterBacking extends BaseBackingBean {

    @Inject
    private UserService service;

    private UserRegisterBindingModel model = new UserRegisterBindingModel();

    public void register() {
        if (service.register(model)) {
            redirect("login");
        }
    }

    public UserRegisterBindingModel getModel() {
        return model;
    }
}
