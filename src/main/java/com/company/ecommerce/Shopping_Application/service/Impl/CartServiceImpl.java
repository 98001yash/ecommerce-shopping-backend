package com.company.ecommerce.Shopping_Application.service.Impl;

import com.company.ecommerce.Shopping_Application.dtos.AddToCartRequest;
import com.company.ecommerce.Shopping_Application.dtos.CartItemDto;
import com.company.ecommerce.Shopping_Application.dtos.CartResponseDto;
import com.company.ecommerce.Shopping_Application.entitiy.Cart;
import com.company.ecommerce.Shopping_Application.entitiy.CartItem;
import com.company.ecommerce.Shopping_Application.entitiy.Product;
import com.company.ecommerce.Shopping_Application.entitiy.User;
import com.company.ecommerce.Shopping_Application.exceptions.ResourceNotFoundException;
import com.company.ecommerce.Shopping_Application.repository.CartItemRepository;
import com.company.ecommerce.Shopping_Application.repository.CartRepository;
import com.company.ecommerce.Shopping_Application.repository.ProductRepository;
import com.company.ecommerce.Shopping_Application.repository.UserRepository;
import com.company.ecommerce.Shopping_Application.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    public void addToCart(AddToCartRequest request, String username) {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        Cart cart = cartRepository.findByUser(user)
                .orElseGet(() -> cartRepository.save(Cart.builder().user(user).totalPrice(0).build()));

        // ✅ Initialize cart items list if null
        if (cart.getItems() == null) {
            cart.setItems(new java.util.ArrayList<>());
        }

        // Check if product already exists in cart
        CartItem existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(product.getId()))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + request.getQuantity());
        } else {
            CartItem cartItem = CartItem.builder()
                    .cart(cart)
                    .product(product)
                    .quantity(request.getQuantity())
                    .build();
            cart.getItems().add(cartItem);
        }

        // Recalculate total price
        double total = cart.getItems().stream()
                .mapToDouble(item -> item.getProduct().getDiscountedPrice() * item.getQuantity())
                .sum();
        cart.setTotalPrice(total);

        cartRepository.save(cart);
    }


    @Override
    public CartResponseDto getCartByUsername(String username) {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        List<CartItemDto> itemDtos = cart.getItems().stream().map(item -> {
            CartItemDto dto = new CartItemDto();
            dto.setId(item.getId());
            dto.setProductId(item.getProduct().getId());
            dto.setTitle(item.getProduct().getTitle());
            dto.setPrice(item.getProduct().getDiscountedPrice());
            dto.setQuantity(item.getQuantity());
            return dto;
        }).toList();

        CartResponseDto response = new CartResponseDto();
        response.setCartId(cart.getId());
        response.setItems(itemDtos);
        response.setTotalPrice(cart.getTotalPrice());

        return response;
    }


    @Override
    public void removeFromCart(Long cartItemId, String username) {
        CartItem item = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart item not found"));
        cartItemRepository.delete(item);
    }

    @Override
    public void clearCart(String username) {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        cart.getItems().clear();
        cart.setTotalPrice(0);
        cartRepository.save(cart);
    }

}
