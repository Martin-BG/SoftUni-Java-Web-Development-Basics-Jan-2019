package fdmcjsf.services;

import fdmcjsf.domain.entities.Cat;
import fdmcjsf.domain.models.binding.CatBindingModel;

public interface CatService extends Service<Cat, String> {

    boolean createCat(CatBindingModel model);
}
