// OrderItemDto.java
package com.company.ecommerce.Shopping_Application.dtos;

import lombok.Data;

@Data
public class OrderItemDto {
    private Long productId;
    private String title;
    private double price;
    private int quantity;
}
