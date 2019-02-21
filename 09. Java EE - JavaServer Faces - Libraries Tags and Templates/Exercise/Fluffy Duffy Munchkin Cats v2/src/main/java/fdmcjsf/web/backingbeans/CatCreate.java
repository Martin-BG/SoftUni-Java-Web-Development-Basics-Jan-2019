package fdmcjsf.web.backingbeans;

import fdmcjsf.domain.models.binding.CatBindingModel;
import fdmcjsf.services.CatService;
import lombok.NoArgsConstructor;

import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.IOException;

@NoArgsConstructor
@Model
public class CatCreate {

    @Inject
    private CatService service;

    private CatBindingModel model = new CatBindingModel();

    public CatBindingModel getModel() {
        return model;
    }

    public void create() throws IOException {
        if (this.service.createCat(model)) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("all");
        }
    }
}
