package com.company.ecommerce.Shopping_Application.controller;

import com.company.ecommerce.Shopping_Application.advices.ApiResponse;
import com.company.ecommerce.Shopping_Application.dtos.AddToCartRequest;
import com.company.ecommerce.Shopping_Application.dtos.CartResponseDto;
import com.company.ecommerce.Shopping_Application.entitiy.Cart;
import com.company.ecommerce.Shopping_Application.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<String>> addToCart(@RequestBody AddToCartRequest request,
                                                         @RequestParam String username) {
        cartService.addToCart(request, username);
        ApiResponse<String> response = new ApiResponse<>("Product added to cart successfully!");
        return ResponseEntity.ok(response);
    }


    @GetMapping
    public ResponseEntity<ApiResponse<CartResponseDto>> getCart(@RequestParam String username) {
        CartResponseDto cartDto = cartService.getCartByUsername(username);
        return ResponseEntity.ok(new ApiResponse<>(cartDto));
    }



    @DeleteMapping("/remove/{itemId}")
    public ResponseEntity<ApiResponse<String>> removeItem(@PathVariable Long itemId, @RequestParam String username) {
        cartService.removeFromCart(itemId, username);
        return ResponseEntity.ok(new ApiResponse<>("Item removed successfully"));
    }


    @DeleteMapping("/clear")
    public ResponseEntity<?> clearCart(@RequestParam String username) {
        cartService.clearCart(username);
        return ResponseEntity.ok("Cart cleared");
    }
}
