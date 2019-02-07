package metube.repository;

import metube.domain.entities.Tube;

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
public class TubeRepositoryImpl implements TubeRepository {

    private final Logger logger;

    @PersistenceContext(unitName = "metube")
    private EntityManager entityManager;

    @Inject
    public TubeRepositoryImpl(Logger logger) {
        this.logger = logger;
    }

    @Override
    public Tube save(Tube entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public Optional<Tube> findByName(String name) {
        try {
            return Optional.of(entityManager
                    .createNamedQuery("Tube.findByName", Tube.class)
                    .setParameter("name", name)
                    .getSingleResult());
        } catch (NoResultException e) {
            logger.log(Level.WARNING, "No Tube found by name " + name, e);
            return Optional.empty();
        }
    }

    @Override
    public List<Tube> findAll() {
        return entityManager
                .createNamedQuery("Tube.findAllOrderByName", Tube.class)
                .getResultList();
    }

    @Override
    public Optional<Tube> findById(String id) {
        try {
            return Optional.of(entityManager
                    .createNamedQuery("BaseEntity.findById", Tube.class)
                    .setParameter("id", id)
                    .getSingleResult());
        } catch (NoResultException e) {
            logger.log(Level.WARNING, "No Tube found by ID " + id, e);
            return Optional.empty();
        }
    }
}
