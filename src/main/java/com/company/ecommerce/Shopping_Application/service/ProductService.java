package com.company.ecommerce.Shopping_Application.service;

import com.company.ecommerce.Shopping_Application.dtos.ProductRequestDto;
import com.company.ecommerce.Shopping_Application.dtos.ProductResponseDto;

import java.util.List;

public interface ProductService {
    ProductResponseDto createProduct(ProductRequestDto request);
    ProductResponseDto getProductById(Long id);
    List<ProductResponseDto> getAllProducts();
    List<ProductResponseDto> getProductsByCategory(Long categoryId);
    ProductResponseDto updateProduct(Long id, ProductRequestDto request);
    void deleteProduct(Long id);
}
