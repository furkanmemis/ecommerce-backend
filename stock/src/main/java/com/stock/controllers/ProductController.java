package com.stock.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stock.models.Product;
import com.stock.services.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import io.swagger.v3.oas.annotations.Operation;

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
    @Operation(description = "Create product")
    public Product CreateProduct(@RequestBody Product product) {
        return this.productService.CreateProduct(product);
    }

    @GetMapping("/get-product/{id}")
    @Operation(description = "Get product by id")
    public Optional<Product> getMethodName(@PathVariable UUID id) {
        return this.productService.GetProductById(id);
    }

    @GetMapping("/get-all")
    @Operation(description = "Get all product")
    public List<Product> GetAllProduct() {
        return this.productService.GetAll();
    }
    
}
