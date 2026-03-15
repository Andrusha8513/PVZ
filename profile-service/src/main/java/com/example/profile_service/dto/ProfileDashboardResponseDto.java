package com.example.profile_service.dto;

import java.util.List;

public record ProfileDashboardResponseDto(
        Long id,
        String name,
        String secondName,
        String surName,
        List<PvzShortDto> pvzs
) {
}
