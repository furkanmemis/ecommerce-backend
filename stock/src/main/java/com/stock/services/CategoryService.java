package com.stock.services;

import java.util.Optional;
import org.springframework.stereotype.Service;
import com.stock.models.Category;
import com.stock.repositories.CategoryRepository;
import java.util.UUID;
import java.util.List;


@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    
    public Category CreateCategory(Category category){
        return categoryRepository.save(category);
    }

    public Optional<Category> GetCategoryById(UUID id){
        return categoryRepository.findById(id);
    }

    public List<Category> GetAll(){
        return categoryRepository.findAll();
    }

}
