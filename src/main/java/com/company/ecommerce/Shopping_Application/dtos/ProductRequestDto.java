package com.company.ecommerce.Shopping_Application.dtos;

import lombok.Data;

@Data
public class ProductRequestDto {
    private String title;
    private String description;
    private double price;
    private double discountedPrice;
    private int quantity;
    private String brand;
    private String color;
    private String size;
    private String imageUrl;
    private Long categoryId;
}
