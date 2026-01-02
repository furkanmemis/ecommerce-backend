package com.stock.stock.models;

import jakarta.persistence.*;
import java.util.UUID;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "Unique products UUID", accessMode = Schema.AccessMode.READ_ONLY)
    private UUID id;

    @Column(nullable = false, unique = true)
    @Schema(description = "Product name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId", nullable = false)
    @Schema(description = "Product category")
    private Category category;

    // getter & setter

    public Product(){}

    public Product(String name, Category category){
        this.name = name;
        this.category = category;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public UUID getId(){
        return this.id;
    }

    public void setCategory(Category category){
        this.category = category;
    }

    public Category getCategory(){
        return this.category;
    }
}
