package com.example.Task.service;

import com.example.Task.Dto.CategoryDto;
import com.example.Task.Dto.ProductDto;
import com.example.Task.exception.EntityException;
import com.example.Task.model.Category;
import com.example.Task.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category addCategory(CategoryDto categoryDto) {
        Category category = new Category(categoryDto.getName());
        return categoryRepository.save(category);
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
         Optional<Category> category = categoryRepository.findByProductId(id);
         if (category.isEmpty()) throw new EntityException("Category with Product " + id + " id does not exist");

         return category.get();
    }


    public Optional<Category> findById(Long categoryId) {
        return  categoryRepository.findById(categoryId);
    }
}
