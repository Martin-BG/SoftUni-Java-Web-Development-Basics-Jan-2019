package metube.repositories;

import metube.domain.entities.User;

import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class UserRepositoryImpl extends BaseCrudRepository<User, String> implements UserRepository {

    private static final Logger LOG = Logger.getLogger(UserRepositoryImpl.class.getName());

    @Override
    protected Logger logger() {
        return LOG;
    }

    @Override
    public Optional<User> findByUsername(String username, boolean eager) {
        String query = eager ? "User.findByUsernameEager" : "User.findByUsername";
        try {
            return Optional.of(entityManager
                    .createNamedQuery(query, User.class)
                    .setParameter("username", username)
                    .getSingleResult());
        } catch (IllegalStateException | PersistenceException e) {
            LOG.log(Level.SEVERE, "No User found by username: " + username, e);
            return Optional.empty();
        }
    }
}
