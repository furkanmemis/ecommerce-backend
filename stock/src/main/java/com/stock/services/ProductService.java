package com.stock.services;

import com.stock.repositories.ProductRepository;
import java.util.Optional;
import com.stock.models.Product;
import java.util.UUID;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ProductService {
    
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public Product CreateProduct(Product product){
        return productRepository.save(product);
    }

    public Optional<Product> GetProductById(UUID id){
        return productRepository.findById(id);
    }
    
    public List<Product> GetAll(){
        return productRepository.findAll();
    }
}
