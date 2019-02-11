package metube.repositories;

import metube.domain.entities.Identifiable;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<ENTITY extends Identifiable<ID>, ID> {

    Optional<ENTITY> create(ENTITY entity);

    Optional<ENTITY> read(ID id);

    Optional<ENTITY> update(ENTITY entity);

    boolean delete(ENTITY entity);

    List<ENTITY> all();
}
