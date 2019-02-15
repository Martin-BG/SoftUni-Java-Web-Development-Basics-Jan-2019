package register.web.mbeans;

import lombok.NoArgsConstructor;
import register.domain.models.binding.employee.EmployeeRegisterBindingModel;
import register.services.EmployeeService;

import javax.enterprise.inject.Model;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.IOException;

@NoArgsConstructor
@Model
public class EmployeeRegisterBean {

    private EmployeeService service;

    private EmployeeRegisterBindingModel bindingModel = new EmployeeRegisterBindingModel();

    @Inject
    public EmployeeRegisterBean(EmployeeService service) {
        this.service = service;
    }

    public void register() throws IOException {
        this.service.saveEmployee(bindingModel);
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect("/");
    }

    public EmployeeRegisterBindingModel getBindingModel() {
        return bindingModel;
    }

    public void setBindingModel(EmployeeRegisterBindingModel bindingModel) {
        this.bindingModel = bindingModel;
    }
}
