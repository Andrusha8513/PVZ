package com.example.profile_service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record EmployeeShiftShortDto(
        Long id,
        LocalDateTime scheduledStartTime,
        LocalDateTime scheduledEndTime,
        String shiftStatus,
        BigDecimal employeeSalary
) {
}
