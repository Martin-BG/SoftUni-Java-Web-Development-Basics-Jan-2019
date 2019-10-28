package panda.repository;

import panda.domain.api.Identifiable;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class BaseCrudRepository<E extends Identifiable<I>, I> implements CrudRepository<E, I> {

    private final Class<E> entityClass;

    @PersistenceContext(unitName = "jtaPersistenceUnit")
    protected EntityManager entityManager;

    protected BaseCrudRepository() {
        entityClass = initEntityClass();
    }

    @SuppressWarnings("unchecked")
    private Class<E> initEntityClass() {
        return (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public Optional<E> create(E entity) {
        try {
            entityManager.persist(entity);
            return Optional.of(entity);
        } catch (EntityExistsException | IllegalArgumentException | TransactionRequiredException e) {
            logger().log(Level.SEVERE, e, () -> "Failed to create new entity: " + entity);
            return Optional.empty();
        }
    }

    @Override
    public Optional<E> read(I id) {
        try {
            return Optional.ofNullable(entityManager.find(entityClass, id));
        } catch (IllegalArgumentException e) {
            logger().log(Level.SEVERE, e, () -> "Invalid arguments for find entity provided: " + id);
            return Optional.empty();
        }
    }

    @Override
    public Optional<E> update(E entity) {
        try {
            return Optional.of(entityManager.merge(entity));
        } catch (IllegalArgumentException | TransactionRequiredException e) {
            logger().log(Level.SEVERE, e, () -> "Entity merge failed: " + entity);
            return Optional.empty();
        }
    }

    @Override
    public boolean delete(E entity) {
        try {
            entityManager.remove(
                    entityManager.contains(entity)
                            ? entity
                            : entityManager.merge(entity));
            return true;
        } catch (IllegalArgumentException | TransactionRequiredException e) {
            logger().log(Level.SEVERE, e, () -> "Entity remove failed: " + entity);
            return false;
        }
    }

    @Override
    public boolean delete(I id) {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaDelete<E> delete = cb.createCriteriaDelete(entityClass);
            Root<E> e = delete.from(entityClass);
            delete.where(cb.equal(e.get("id"), id));
            int deleted = entityManager.createQuery(delete).executeUpdate();
            return deleted > 0;
        } catch (IllegalStateException | IllegalArgumentException | PersistenceException e) {
            logger().log(Level.SEVERE, e, () -> "Failed to delete entity by id " + id);
            return false;
        }
    }

    @Override
    public List<E> all() {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<E> cq = cb.createQuery(entityClass);
            Root<E> rootEntry = cq.from(entityClass);
            CriteriaQuery<E> all = cq.select(rootEntry);
            TypedQuery<E> allQuery = entityManager.createQuery(all);
            return allQuery.getResultList();
        } catch (IllegalStateException | IllegalArgumentException e) {
            logger().log(Level.SEVERE, e, () -> "Retrieving of all entities failed");
            return List.of();
        }
    }

    @Override
    public Long count() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        criteria.select(builder.count(criteria.from(entityClass)));
        TypedQuery<Long> query = entityManager.createQuery(criteria);

        return query.getSingleResult();
    }

    @Override
    public boolean isEmpty() {
        return count() == 0L;
    }

    protected abstract Logger logger();
}
