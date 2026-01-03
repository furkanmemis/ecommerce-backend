package com.stock.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stock.models.Category;
import com.stock.services.CategoryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }
    
    @PostMapping("/create")
    public Category CreateCategory(@RequestBody Category category){
        return this.categoryService.CreateCategory(category);
    }

    @GetMapping("/get-category")
    public Optional<Category> getMethodName(@RequestParam UUID id) {
        return this.categoryService.GetCategoryById(id);
    }

    @GetMapping("/get-all")
    public List<Category> getMethodName() {
        return this.categoryService.GetAll();
    }
    
    

    
    
}
