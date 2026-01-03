package com.stock.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stock.models.Product;
import com.stock.services.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/api/product")
public class ProductController {
    
    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping("/create")
    public Product CreateProduct(@RequestBody Product product) {
        return this.productService.CreateProduct(product);
    }

    @GetMapping("/get-product/{id}")
    public Optional<Product> getMethodName(@PathVariable UUID id) {
        return this.productService.GetProductById(id);
    }

    @GetMapping("/get-all")
    public List<Product> GetAllProduct() {
        return this.productService.GetAll();
    }
    
}
