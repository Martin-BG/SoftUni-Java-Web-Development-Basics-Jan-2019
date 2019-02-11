package metube.services;

import metube.domain.entities.Identifiable;
import metube.domain.models.binding.Bindable;
import metube.domain.models.view.Viewable;
import metube.repositories.CrudRepository;
import org.modelmapper.ModelMapper;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

class BaseService<ENTITY extends Identifiable<ID>, ID, REPOSITORY extends CrudRepository<ENTITY, ID>>
        implements Service<ENTITY, ID> {

    protected final ModelMapper mapper;
    protected final Logger logger;
    protected final REPOSITORY repository;
    private final Class<ENTITY> entityClass;
    private final Validator validator;

    protected BaseService(ModelMapper mapper, Validator validator, Logger logger, REPOSITORY repository) {
        entityClass = initEntityClass();
        this.mapper = mapper;
        this.validator = validator;
        this.logger = logger;
        this.repository = repository;
    }

    protected final <MODEL extends Bindable<ENTITY>> boolean create(MODEL model) {
        return validateModel(model) && repository.create(mapper.map(model, entityClass)).isPresent();
    }

    @Override
    public final <MODEL extends Viewable<ENTITY>> Optional<MODEL> findById(ID id, Class<MODEL> clazz) {
        return repository
                .read(id)
                .map(e -> mapper.map(e, clazz));
    }

    @Override
    public final <MODEL extends Viewable<ENTITY>> List<MODEL> findAll(Class<MODEL> clazz) {
        return repository
                .all()
                .stream()
                .map(t -> mapper.map(t, clazz))
                .collect(Collectors.toList());
    }

    private <MODEL extends Bindable<ENTITY>> boolean validateModel(MODEL model) {
        Set<ConstraintViolation<MODEL>> violations = validator.validate(model);
        if (!violations.isEmpty()) {
            String msg = "Failed validation on:\r\n\t" +
                    violations.stream()
                            .map(cv -> cv.getPropertyPath().toString()
                                    + " (" + cv.getInvalidValue() + ") " + cv.getMessage())
                            .collect(Collectors.joining("\r\n\t"));
            logger.log(Level.SEVERE, msg);
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    private Class<ENTITY> initEntityClass() {
        return (Class<ENTITY>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
