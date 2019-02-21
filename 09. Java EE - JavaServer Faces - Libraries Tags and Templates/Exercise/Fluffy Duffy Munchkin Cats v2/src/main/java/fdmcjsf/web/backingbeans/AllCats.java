package fdmcjsf.web.backingbeans;

import fdmcjsf.domain.models.view.CatViewModel;
import fdmcjsf.services.CatService;
import lombok.NoArgsConstructor;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@NoArgsConstructor
@Model
public class AllCats {

    private List<CatViewModel> cats;

    @Inject
    public AllCats(CatService service) {
        cats = service.findAll(CatViewModel.class);
    }

    public List<CatViewModel> getCats() {
        return Collections.unmodifiableList(cats);
    }
}
