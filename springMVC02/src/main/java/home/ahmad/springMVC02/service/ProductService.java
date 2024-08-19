package home.ahmad.springMVC02.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import home.ahmad.springMVC02.model.Product;
import home.ahmad.springMVC02.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

/**
 * 
 * @author Ahmad Alrefai
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}
