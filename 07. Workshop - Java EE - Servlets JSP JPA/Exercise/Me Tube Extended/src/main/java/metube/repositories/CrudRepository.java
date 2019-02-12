package metube.repositories;

import metube.domain.entities.Identifiable;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<E extends Identifiable<I>, I> {

    Optional<E> create(E entity);

    Optional<E> read(I id);

    Optional<E> update(E entity);

    boolean delete(E entity);

    List<E> all();
}
