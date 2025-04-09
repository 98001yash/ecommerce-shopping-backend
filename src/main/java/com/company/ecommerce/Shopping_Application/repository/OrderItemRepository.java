package com.company.ecommerce.Shopping_Application.repository;

import com.company.ecommerce.Shopping_Application.entitiy.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
