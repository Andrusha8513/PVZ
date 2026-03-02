package com.example.profile_service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ShiftResponseDto(
        Long id,
        Long employeeId,
        String employeeFullName,
        LocalDateTime scheduledStartTime,
        LocalDateTime scheduledEndTime,
        LocalDateTime actualStartTime,
        LocalDateTime actualEndTime,
        String status,
        BigDecimal bonus,
        BigDecimal penalty,
        String penaltyReason,
        BigDecimal calculatedPay
) {}