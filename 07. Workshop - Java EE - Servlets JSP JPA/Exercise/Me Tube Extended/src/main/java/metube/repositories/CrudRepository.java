package metube.repositories;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<ENTITY, ID> {

    Optional<ENTITY> create(ENTITY entity);

    Optional<ENTITY> read(ID id);

    Optional<ENTITY> update(ENTITY entity);

    boolean delete(ENTITY entity);

    List<ENTITY> all();
}
