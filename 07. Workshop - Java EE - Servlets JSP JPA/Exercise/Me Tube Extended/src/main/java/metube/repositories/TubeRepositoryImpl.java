package metube.repositories;

import metube.domain.entities.Tube;

import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class TubeRepositoryImpl extends BaseCrudRepository<Tube, String> implements TubeRepository {

    private static final Logger LOG = Logger.getLogger(TubeRepositoryImpl.class.getName());

    @Override
    protected Logger logger() {
        return LOG;
    }

    @Override
    public Optional<Tube> findByName(String name) {
        try {
            return Optional.of(entityManager
                    .createNamedQuery("Tube.findByTitle", Tube.class)
                    .setParameter("title", name)
                    .getSingleResult());
        } catch (IllegalStateException | PersistenceException e) {
            LOG.log(Level.SEVERE, "No Tube found by name: " + name, e);
            return Optional.empty();
        }
    }
}
