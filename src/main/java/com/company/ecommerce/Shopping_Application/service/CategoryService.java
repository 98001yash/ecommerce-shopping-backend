package com.company.ecommerce.Shopping_Application.service;

import com.company.ecommerce.Shopping_Application.dtos.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);
    List<CategoryDto> getAllCategories();
    CategoryDto getCategoryById(Long id);
    void deleteCategory(Long id);
}
