package chushka.service;

import chushka.domain.entities.Product;
import chushka.domain.models.service.ProductServiceModel;
import chushka.domain.models.view.ProductVewModel;
import chushka.repository.ProductRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Inject
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveProduct(ProductServiceModel productServiceModel) {
        Product product = modelMapper.map(productServiceModel, Product.class);
        productRepository.save(product);
    }

    @Override
    public List<ProductServiceModel> findAllProducts() {
        return this.productRepository
                .findAll()
                .stream()
                .map(product -> modelMapper.map(product, ProductServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductVewModel> findByName(String name) {
        Optional<Product> product = productRepository.findByName(name);
        if (product.isPresent()) {
            ProductVewModel model = modelMapper.map(product.get(), ProductVewModel.class);
            if (product.get().getType() != null) {
                model.setType(product.get().getType().getLabel());
            }
            return Optional.of(model);
        }
        return Optional.empty();
    }
}
