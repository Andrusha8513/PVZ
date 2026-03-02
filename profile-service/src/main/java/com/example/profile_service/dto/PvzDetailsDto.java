package com.example.profile_service.dto;

import java.util.List;

public record PvzDetailsDto(
        Long id,
        String name,
        String address,
        List<EmployeeResponseDto> employees
) {}
