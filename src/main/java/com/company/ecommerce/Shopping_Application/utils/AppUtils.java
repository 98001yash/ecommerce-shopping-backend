package com.company.ecommerce.Shopping_Application.utils;


import com.company.ecommerce.Shopping_Application.entitiy.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class AppUtils {

    public static User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}

