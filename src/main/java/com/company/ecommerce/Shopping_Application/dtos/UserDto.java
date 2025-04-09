package com.company.ecommerce.Shopping_Application.dtos;

import com.company.ecommerce.Shopping_Application.enums.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDto {
    private Long id;
    private String email;
    private String name;
    private Gender gender;
    private LocalDate dateOfBirth;
}
