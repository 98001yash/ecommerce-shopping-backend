package com.company.ecommerce.Shopping_Application.service.Impl;

import com.company.ecommerce.Shopping_Application.entitiy.*;
import com.company.ecommerce.Shopping_Application.exceptions.ResourceNotFoundException;
import com.company.ecommerce.Shopping_Application.repository.*;
import com.company.ecommerce.Shopping_Application.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;

    @Override
    public Order placeOrder(String username) {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        if (cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        Order order = new Order();
        order.setUser(user);
        order.setCreatedAt(LocalDateTime.now());
        order.setTotalAmount(cart.getTotalPrice());

        List<OrderItem> orderItems = cart.getItems().stream().map(cartItem -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getProduct().getDiscountedPrice());
            orderItem.setOrder(order);
            return orderItem;
        }).collect(Collectors.toList());

        order.setOrderItems(orderItems);
        orderRepository.save(order);
        orderItemRepository.saveAll(orderItems);

        // Clear cart after placing order
        cart.getItems().clear();
        cart.setTotalPrice(0);
        cartRepository.save(cart);

        return order;
    }

    @Override
    public List<Order> getUserOrders(String username) {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return orderRepository.findByUser(user);
    }
}
