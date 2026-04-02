package com.stock.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class ProductCreate {
    
    @Schema(description = "Product name")
    private String name;

    @Schema(description = "Product category id")
    private String categoryId;

    public String getName(){
        return this.name;
    }

    public String getCategoryId(){
        return this.categoryId;
    }
}