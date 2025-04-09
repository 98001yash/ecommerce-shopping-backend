package com.company.ecommerce.Shopping_Application.controller;

import com.company.ecommerce.Shopping_Application.entitiy.Order;
import com.company.ecommerce.Shopping_Application.service.OrderService;
import com.company.ecommerce.Shopping_Application.advices.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/place")
    public ResponseEntity<ApiResponse<Order>> placeOrder(@RequestParam String username) {
        Order order = orderService.placeOrder(username);
        return ResponseEntity.ok(new ApiResponse<>(order));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Order>>> getUserOrders(@RequestParam String username) {
        List<Order> orders = orderService.getUserOrders(username);
        return ResponseEntity.ok(new ApiResponse<>(orders));
    }
}
