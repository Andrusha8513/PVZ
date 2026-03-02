package com.example.profile_service.dto;

import java.math.BigDecimal;

public record EmployeeResponseDto(
        Long id ,
        String name,
        String secondName,
        String surName,
        String phone,
        BigDecimal fixedRatePerHour
) {
}
