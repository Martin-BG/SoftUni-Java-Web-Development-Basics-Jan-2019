package panda.service;

import panda.domain.api.Identifiable;
import panda.domain.api.Viewable;

import java.util.List;
import java.util.Optional;

public interface Service<E extends Identifiable<I>, I> {

    <M extends Viewable<E>> Optional<M> findById(I id, Class<M> clazz);

    <M extends Viewable<E>> List<M> findAll(Class<M> clazz);
}
