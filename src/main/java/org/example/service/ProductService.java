package org.example.service;
import org.example.entity.Product;
import java.util.List;


public interface ProductService {
        List<Product> getAllProducts();

    Product addProduct(Product product);

    Product getProductById(Long id);

    Product updateProduct(Long id, Product product);

    void deleteProduct(Long id);
}