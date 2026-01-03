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
    private final StockService stockService;

    public ProductService(ProductRepository productRepository, StockService stockService){
        this.productRepository = productRepository;
        this.stockService = stockService;
    }

    public Product CreateProduct(Product product){
        Product saved = this.productRepository.save(product);
        this.SendSocket();
        return saved;
    }

    public Optional<Product> GetProductById(UUID id){
        return productRepository.findById(id);
    }
    
    public List<Product> GetAll(){
        return productRepository.findAll();
    }

    private void SendSocket(){
        List<Product> products = this.GetAll();
        this.stockService.pushStock(products);
    }
}
