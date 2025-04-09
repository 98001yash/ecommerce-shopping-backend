package com.company.ecommerce.Shopping_Application.repository;

import com.company.ecommerce.Shopping_Application.entitiy.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}