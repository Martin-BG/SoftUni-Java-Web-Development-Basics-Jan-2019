package metube.repository;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<E, I> {

    E save(E e);

    Optional<E> findById(I i);

    List<E> findAll();
}
