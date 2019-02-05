package chushka.service;

import chushka.domain.models.service.ProductServiceModel;
import chushka.domain.models.view.ProductVewModel;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    void saveProduct(ProductServiceModel productServiceModel);

    List<ProductServiceModel> findAllProducts();

    Optional<ProductVewModel> findByName(String name);
}
