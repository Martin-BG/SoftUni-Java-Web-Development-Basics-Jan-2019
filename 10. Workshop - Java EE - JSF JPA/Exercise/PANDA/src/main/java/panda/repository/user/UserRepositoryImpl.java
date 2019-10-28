package panda.repository.user;

import panda.domain.entity.User;
import panda.repository.BaseCrudRepository;

import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class UserRepositoryImpl extends BaseCrudRepository<User, UUID> implements UserRepository {

    private static final Logger LOG = Logger.getLogger(UserRepositoryImpl.class.getName());

    @Override
    protected Logger logger() {
        return LOG;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        try {
            return Optional.of(entityManager
                    .createNamedQuery("User.findByUsername", User.class)
                    .setParameter("username", username)
                    .getSingleResult());
        } catch (IllegalStateException | PersistenceException e) {
            LOG.log(Level.SEVERE, e, () -> "No User found by username: " + username);
            return Optional.empty();
        }
    }
}
