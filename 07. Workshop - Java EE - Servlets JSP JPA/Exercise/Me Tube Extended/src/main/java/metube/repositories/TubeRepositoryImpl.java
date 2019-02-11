package metube.repositories;

import metube.domain.entities.Tube;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class TubeRepositoryImpl extends BaseCrudRepository<Tube, String> implements TubeRepository {

    @Inject
    public TubeRepositoryImpl(Logger logger) {
        super(logger);
    }

    @Override
    public Optional<Tube> findByName(String name) {
        try {
            return Optional.of(entityManager
                    .createNamedQuery("Tube.findByTitle", Tube.class)
                    .setParameter("title", name)
                    .getSingleResult());
        } catch (IllegalStateException | PersistenceException e) {
            logger.log(Level.SEVERE, "No Tube found by name: " + name, e);
            return Optional.empty();
        }
    }
}


