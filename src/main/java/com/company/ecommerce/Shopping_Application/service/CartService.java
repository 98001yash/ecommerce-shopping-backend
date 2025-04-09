package com.company.ecommerce.Shopping_Application.service;

import com.company.ecommerce.Shopping_Application.dtos.AddToCartRequest;
import com.company.ecommerce.Shopping_Application.dtos.CartResponseDto;
import com.company.ecommerce.Shopping_Application.entitiy.Cart;

public interface CartService {
    void addToCart(AddToCartRequest request, String username);
    CartResponseDto getCartByUsername(String username);
    void removeFromCart(Long cartItemId, String username);
    void clearCart(String username);
}
