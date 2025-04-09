package com.company.ecommerce.Shopping_Application.repository;

import com.company.ecommerce.Shopping_Application.entitiy.Cart;
import com.company.ecommerce.Shopping_Application.entitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);
}