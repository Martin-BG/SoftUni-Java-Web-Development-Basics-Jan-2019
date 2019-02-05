package chushka.repository;

import chushka.domain.entities.Product;

import java.util.Optional;

public interface ProductRepository extends GenericRepository<Product, String> {

    Optional<Product> findByName(String name);
}
