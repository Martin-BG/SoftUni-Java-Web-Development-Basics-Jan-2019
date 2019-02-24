package org.softuni.exam.repositories;

import org.softuni.exam.domain.entities.Identifiable;

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

abstract class BaseCrudRepository<E extends Identifiable<I>, I> implements CrudRepository<E, I> {

    private final Class<E> entityClass;

    @PersistenceContext(unitName = "jtaPersistenceUnit")
    protected EntityManager entityManager;

    protected BaseCrudRepository() {
        entityClass = initEntityClass();
    }

    protected abstract Logger logger();

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
            logger().log(Level.SEVERE, "Failed to delete entity by id " + id, e);
            return false;
        }
    }

    @Override
    public Optional<E> create(E entity) {
        try {
            entity.setId(null); // Ensure that id is null - ModelMapper tends to assign values to this field
            entityManager.persist(entity);
            return Optional.of(entity);
        } catch (EntityExistsException | IllegalArgumentException | TransactionRequiredException e) {
            logger().log(Level.SEVERE, "Failed to create new entity: " + entity, e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<E> read(I id) {
        try {
            return Optional.ofNullable(entityManager.find(entityClass, id));
        } catch (IllegalArgumentException e) {
            logger().log(Level.SEVERE, "Invalid arguments for find entity provided: " + id, e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<E> update(E entity) {
        try {
            return Optional.of(entityManager.merge(entity));
        } catch (IllegalArgumentException | TransactionRequiredException e) {
            logger().log(Level.SEVERE, "Entity merge failed: " + entity, e);
            return Optional.empty();
        }
    }

    @Override
    public boolean delete(E entity) {
        try {
            entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
            return true;
        } catch (IllegalArgumentException | TransactionRequiredException e) {
            logger().log(Level.SEVERE, "Entity remove failed: " + entity, e);
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
            logger().log(Level.SEVERE, "Retrieving of all entities failed", e);
            return List.of();
        }
    }

    @SuppressWarnings("unchecked")
    private Class<E> initEntityClass() {
        return (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
