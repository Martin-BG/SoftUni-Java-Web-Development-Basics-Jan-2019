package chushka.repository;

import chushka.domain.entities.Product;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class ProductRepositoryImpl implements ProductRepository {

    private final Logger logger;
    @PersistenceContext(unitName = "chushka")
    private EntityManager entityManager;

    @Inject
    public ProductRepositoryImpl(Logger logger) {
        this.logger = logger;
    }

    @Override
    public Product save(Product entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public Optional<Product> findById(String id) {
        try {
            return Optional.of(entityManager
                    .createQuery("SELECT p FROM products p WHERE p.id = :id", Product.class)
                    .setParameter("id", id)
                    .getSingleResult());
        } catch (NoResultException e) {
            logger.log(Level.WARNING, "No Product found by ID " + id, e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<Product> findByName(String name) {
        try {
            return Optional.of(entityManager
                    .createQuery("SELECT p FROM products p WHERE p.name = :name", Product.class)
                    .setParameter("name", name)
                    .getSingleResult());
        } catch (NoResultException e) {
            logger.log(Level.WARNING, "No Product found by name " + name, e);
            return Optional.empty();
        }
    }

    @Override
    public List<Product> findAll() {
        return entityManager
                .createQuery("SELECT p FROM products p", Product.class)
                .getResultList();
    }
}
