package com.company.ecommerce.Shopping_Application.repository;

import com.company.ecommerce.Shopping_Application.entitiy.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
