package metube.repositories;

import metube.domain.entities.Identifiable;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

class BaseCrudRepository<ENTITY extends Identifiable<ID>, ID> implements CrudRepository<ENTITY, ID> {

    protected final Logger logger;
    private final Class<ENTITY> entityClass;

    @PersistenceContext(unitName = "metube")
    protected EntityManager entityManager;

    protected BaseCrudRepository(Logger logger) {
        entityClass = initEntityClass();
        this.logger = logger;
    }

    @Override
    public Optional<ENTITY> create(ENTITY entity) {
        try {
            entity.setId(null); // Ensure that id is null - ModelMapper tends to assign values to this field
            entityManager.persist(entity);
            return Optional.of(entity);
        } catch (EntityExistsException | IllegalArgumentException | TransactionRequiredException e) {
            logger.log(Level.SEVERE, "Failed to create new entity: " + entity, e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<ENTITY> read(ID id) {
        try {
            return Optional.ofNullable(entityManager.find(entityClass, id));
        } catch (IllegalArgumentException e) {
            logger.log(Level.SEVERE, "Invalid arguments for find entity provided: " + id, e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<ENTITY> update(ENTITY entity) {
        try {
            return Optional.of(entityManager.merge(entity));
        } catch (IllegalArgumentException | TransactionRequiredException e) {
            logger.log(Level.SEVERE, "Entity merge failed: " + entity, e);
            return Optional.empty();
        }
    }

    @Override
    public boolean delete(ENTITY entity) {
        try {
            entityManager.remove(entity);
            return true;
        } catch (IllegalArgumentException | TransactionRequiredException e) {
            logger.log(Level.SEVERE, "Entity remove failed: " + entity, e);
            return false;
        }
    }

    @Override
    public List<ENTITY> all() {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<ENTITY> cq = cb.createQuery(entityClass);
            Root<ENTITY> rootEntry = cq.from(entityClass);
            CriteriaQuery<ENTITY> all = cq.select(rootEntry);
            TypedQuery<ENTITY> allQuery = entityManager.createQuery(all);
            return allQuery.getResultList();
        } catch (IllegalStateException | IllegalArgumentException e) {
            logger.log(Level.SEVERE, "Retrieving of all entities failed", e);
            return List.of();
        }
    }

    @SuppressWarnings("unchecked")
    private Class<ENTITY> initEntityClass() {
        return (Class<ENTITY>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
