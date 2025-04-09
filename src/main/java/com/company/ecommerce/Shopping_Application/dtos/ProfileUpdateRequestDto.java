package com.company.ecommerce.Shopping_Application.dtos;


import com.company.ecommerce.Shopping_Application.enums.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProfileUpdateRequestDto {
    private String name;
    private LocalDate dateOfBirth;
    private Gender gender;
}