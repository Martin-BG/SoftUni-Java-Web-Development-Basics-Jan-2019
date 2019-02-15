package register.repositories;


import register.domain.entities.Identifiable;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<E extends Identifiable<I>, I> {

    Optional<E> create(E entity);

    Optional<E> read(I id);

    Optional<E> update(E entity);

    boolean delete(E entity);

    boolean delete(I id);

    List<E> all();
}
