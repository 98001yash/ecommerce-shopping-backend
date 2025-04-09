package com.company.ecommerce.Shopping_Application.dtos;

import lombok.Data;

import java.util.List;

@Data
public class CartResponseDto {
    private Long cartId;
    private List<CartItemDto> items;
    private double totalPrice;
}
