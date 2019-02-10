package metube.services;

import metube.domain.models.binding.Bindable;
import metube.domain.models.view.Viewable;

import java.util.List;

public interface Service<ENTITY, ID> {

    <MODEL extends Bindable<ENTITY>> void save(MODEL model);

    <MODEL extends Viewable<ENTITY>> MODEL findById(ID id, Class<MODEL> clazz);

    <MODEL extends Viewable<ENTITY>> List<MODEL> findAll(Class<MODEL> clazz);
}
