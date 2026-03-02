package com.example.profile_service.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateShiftRequestDto(
        @NotNull(message = "Укажите сотрудника")
        Long employeeId,

        @NotNull(message = "Укажите время начала")
        LocalDateTime scheduledStartTime,

        @NotNull(message = "Укажите время окончания")
        LocalDateTime scheduledEndTime
) {}
