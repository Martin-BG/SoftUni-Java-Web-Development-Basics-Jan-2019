package metube.services;

import metube.domain.entities.Identifiable;
import metube.domain.models.view.Viewable;

import java.util.List;
import java.util.Optional;

public interface Service<ENTITY extends Identifiable<ID>, ID> {

    <MODEL extends Viewable<ENTITY>> Optional<MODEL> findById(ID id, Class<MODEL> clazz);

    <MODEL extends Viewable<ENTITY>> List<MODEL> findAll(Class<MODEL> clazz);
}
