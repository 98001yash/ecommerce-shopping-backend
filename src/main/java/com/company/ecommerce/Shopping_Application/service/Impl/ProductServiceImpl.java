package com.company.ecommerce.Shopping_Application.service.Impl;

import com.company.ecommerce.Shopping_Application.dtos.ProductRequestDto;
import com.company.ecommerce.Shopping_Application.dtos.ProductResponseDto;
import com.company.ecommerce.Shopping_Application.entitiy.Category;
import com.company.ecommerce.Shopping_Application.entitiy.Product;
import com.company.ecommerce.Shopping_Application.exceptions.ResourceNotFoundException;
import com.company.ecommerce.Shopping_Application.repository.CategoryRepository;
import com.company.ecommerce.Shopping_Application.repository.ProductRepository;
import com.company.ecommerce.Shopping_Application.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public ProductResponseDto createProduct(ProductRequestDto request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        Product product = modelMapper.map(request, Product.class);
        product.setId(null); // 👈 important line to prevent update attempt
        product.setCategory(category);

        return modelMapper.map(productRepository.save(product), ProductResponseDto.class);
    }


    @Override
    public ProductResponseDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        return modelMapper.map(product, ProductResponseDto.class);
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> modelMapper.map(product, ProductResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponseDto> getProductsByCategory(Long categoryId) {
        return productRepository.findByCategoryId(categoryId).stream()
                .map(product -> modelMapper.map(product, ProductResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDto updateProduct(Long id, ProductRequestDto request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        modelMapper.map(request, product);

        if (request.getCategoryId() != null) {
            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
            product.setCategory(category);
        }

        return modelMapper.map(productRepository.save(product), ProductResponseDto.class);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        productRepository.delete(product);
    }

    @Override
    public Page<ProductResponseDto> searchProducts(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findByNameContainingIgnoreCase(keyword, pageable)
                .map(product -> modelMapper.map(product, ProductResponseDto.class));
    }

    @Override
    public Page<ProductResponseDto> filterByPrice(double min, double max, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findByPriceBetween(min, max, pageable)
                .map(product -> modelMapper.map(product, ProductResponseDto.class));
    }

    @Override
    public Page<ProductResponseDto> filterByCategory(Long categoryId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findByCategoryId(categoryId, pageable)
                .map(product -> modelMapper.map(product, ProductResponseDto.class));
    }

}
