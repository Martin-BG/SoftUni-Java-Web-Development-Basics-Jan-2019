package sbojbg.repositories;

import sbojbg.domain.entities.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {

    Optional<User> findByUsername(String username);
}
