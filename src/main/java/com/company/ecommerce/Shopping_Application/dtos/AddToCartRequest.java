package com.company.ecommerce.Shopping_Application.dtos;

import lombok.Data;

@Data
public class AddToCartRequest {
    private Long productId;
    private int quantity;
}
