package panda.service;

import org.modelmapper.ModelMapper;
import panda.domain.api.Bindable;
import panda.domain.api.Identifiable;
import panda.domain.api.Viewable;
import panda.repository.CrudRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public abstract class BaseService<E extends Identifiable<I>, I, R extends CrudRepository<E, I>> implements Service<E, I> {

    protected final Validator validator;
    protected final ModelMapper mapper;
    protected final R repository;
    private final Class<E> entityClass;

    protected BaseService(ModelMapper mapper, Validator validator, R repository) {
        entityClass = initEntityClass();
        this.mapper = mapper;
        this.validator = validator;
        this.repository = repository;
    }

    @SuppressWarnings("unchecked")
    private Class<E> initEntityClass() {
        return (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected final <M extends Bindable<E>> boolean create(M model) {
        E map = mapper.map(model, entityClass);
        return validateModel(model) && repository.create(map).isPresent();
    }

    private <M extends Bindable<E>> boolean validateModel(M model) {
        Set<ConstraintViolation<M>> violations = validator.validate(model);
        if (!violations.isEmpty()) {
            logger().log(Level.SEVERE, () ->
                    "Failed validation on:\r\n\t" +
                            violations.stream()
                                    .map(cv -> cv.getPropertyPath().toString()
                                            + " (" + cv.getInvalidValue() + ") " + cv.getMessage())
                                    .collect(Collectors.joining("\r\n\t")));
            return false;
        }
        return true;
    }

    protected abstract Logger logger();

    @Override
    public final <M extends Viewable<E>> Optional<M> findById(I id, Class<M> clazz) {
        return repository
                .read(id)
                .map(e -> mapper.map(e, clazz));
    }

    @Override
    public final <M extends Viewable<E>> List<M> findAll(Class<M> clazz) {
        return repository
                .all()
                .stream()
                .map(t -> mapper.map(t, clazz))
                .collect(Collectors.toList());
    }
}
