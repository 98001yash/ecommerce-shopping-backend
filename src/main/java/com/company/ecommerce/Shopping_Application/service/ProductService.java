package com.company.ecommerce.Shopping_Application.service;

import com.company.ecommerce.Shopping_Application.dtos.ProductRequestDto;
import com.company.ecommerce.Shopping_Application.dtos.ProductResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    ProductResponseDto createProduct(ProductRequestDto request);
    ProductResponseDto getProductById(Long id);
    List<ProductResponseDto> getAllProducts();
    List<ProductResponseDto> getProductsByCategory(Long categoryId);
    ProductResponseDto updateProduct(Long id, ProductRequestDto request);
    void deleteProduct(Long id);

    Page<ProductResponseDto> searchProducts(String keyword, int page, int size);

    Page<ProductResponseDto> filterByPrice(double min, double max, int page, int size);

    Page<ProductResponseDto> filterByCategory(Long categoryId, int page, int size);

}
