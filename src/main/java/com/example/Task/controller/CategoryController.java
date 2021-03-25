package com.example.Task.controller;

import com.example.Task.Dto.CategoryDto;
import com.example.Task.model.Category;
import com.example.Task.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/add")
    public Category addCategory(@RequestBody CategoryDto categoryDto) {
        return categoryService.addCategory(categoryDto);
    }

    @GetMapping("/list")
    public List<Category> getCategories() {
        return categoryService.getCategories();
    }

    @GetMapping
    public Category getCategoryByProductId(@RequestParam Long product_id) {
        return  categoryService.getCategoryById(product_id);
    }
}
