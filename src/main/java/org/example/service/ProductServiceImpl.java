package org.example.service;

import jakarta.annotation.PostConstruct;
import org.example.entity.Product;
import org.example.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Product> getAllProducts() {
        return repository.findAll();
    }
    @Override
    public Product addProduct(Product product) {
        return repository.save(product);
    }
    @Override
    public Product getProductById(Long id) {
        return repository.findById(id).orElse(null);
    }
    @Override
    public Product updateProduct(Long id, Product product) {

        Product existing = repository.findById(id).orElse(null);

        if (existing == null) {
            return null;   // we’ll improve this later with exceptions
        }

        existing.setName(product.getName());
        existing.setPrice(product.getPrice());
        existing.setQuantity(product.getQuantity());

        return repository.save(existing);
    }
    @Override
    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }
}
