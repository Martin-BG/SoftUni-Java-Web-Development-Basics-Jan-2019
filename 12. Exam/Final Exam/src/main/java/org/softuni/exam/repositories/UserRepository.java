package org.softuni.exam.repositories;

import org.softuni.exam.domain.entities.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {

    Optional<User> findByUsername(String username);
}
