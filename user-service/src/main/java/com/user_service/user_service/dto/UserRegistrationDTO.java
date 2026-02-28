package com.user_service.user_service.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserRegistrationDTO {
    private String name;
    private String secondName;
    private String password;
    private String email;
    private String surName;
}
