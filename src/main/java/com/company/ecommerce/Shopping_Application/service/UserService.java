package com.company.ecommerce.Shopping_Application.service;


import com.company.ecommerce.Shopping_Application.dtos.ProfileUpdateRequestDto;
import com.company.ecommerce.Shopping_Application.dtos.UserDto;
import com.company.ecommerce.Shopping_Application.entitiy.User;

public interface UserService {

    User getUserById(Long id);

    void updateProfile(ProfileUpdateRequestDto profileUpdateRequestDto);

    UserDto getMyProfile();
}
