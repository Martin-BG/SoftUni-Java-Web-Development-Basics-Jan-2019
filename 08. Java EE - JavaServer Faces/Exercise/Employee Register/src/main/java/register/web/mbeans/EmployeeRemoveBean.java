package register.web.mbeans;

import lombok.NoArgsConstructor;
import register.services.EmployeeService;

import javax.enterprise.inject.Model;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.IOException;

@NoArgsConstructor
@Model
public class EmployeeRemoveBean {

    private EmployeeService service;

    @Inject
    public EmployeeRemoveBean(EmployeeService service) {
        this.service = service;
    }

    public void remove(String id) throws IOException {
        service.deleteEmployee(id);
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect("/");
    }
}
