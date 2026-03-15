package com.example.profile_service.dto;

import com.example.profile_service.entity.ShiftStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ShiftResponseDto(
        Long id,
        Long employeeId,
        String employeeName,
        String employeeSecondName,
        String employeeSurName,
        LocalDateTime scheduledStartTime,
        LocalDateTime scheduledEndTime,
        LocalDateTime actualStartTime,
        LocalDateTime actualEndTime,
        ShiftStatus shiftStatus,
        BigDecimal bonus,
        BigDecimal penalty,
        String penaltyReason,
        BigDecimal calculatedPay
) {}