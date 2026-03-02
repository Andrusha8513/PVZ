package com.example.profile_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CreateEmployeeRequestDto(

        @NotBlank(message = "Имя сотрудника не может быть пустым!")
        String name,

        @NotBlank(message = "Фамилия сотрудника не может быть пустым!")
        String secondName,

        @NotBlank(message = "Отчество сотрудника не может быть пустым!")
        String surName,

        @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$" , message = "Некорректный номер телефона!")
        String phone,

        @NotNull(message = "Укажите ставку")
        @Positive(message = "Ставка не может быть меньше нуля")
        BigDecimal fixedRatePerHour

) {
}
