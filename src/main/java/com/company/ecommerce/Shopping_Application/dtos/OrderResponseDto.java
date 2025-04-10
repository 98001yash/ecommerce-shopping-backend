// OrderResponseDto.java
package com.company.ecommerce.Shopping_Application.dtos;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponseDto {
    private Long orderId;
    private String orderNumber;
    private double totalAmount;
    private String shippingAddress;
    private String status;
    private LocalDateTime createdAt;
    private List<OrderItemDto> items;
}
