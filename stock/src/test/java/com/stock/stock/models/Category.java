package com.stock.stock.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "Unique category UUID", accessMode = Schema.AccessMode.READ_ONLY)
    private UUID id;

    @Column(nullable = false, unique = true)
    @Schema(description = "Category name")
    private String name;

    public Category(){}

    public Category(String name){
        this.name = name;
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
}
