package com.example.profile_service.mapper;

import com.example.profile_service.dto.CreateShiftRequestDto;
import com.example.profile_service.dto.EmployeeShiftShortDto;
import com.example.profile_service.dto.ShiftResponseDto;
import com.example.profile_service.dto.ShiftShortDto;
import com.example.profile_service.entity.Shift;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ShiftMapper {
    Shift toEntity(CreateShiftRequestDto createShiftRequestDto);

    @Mapping(source = "employee.id", target = "employeeId")
    @Mapping(source = "employeeSalary", target = "calculatedPay")
    @Mapping(source = "employee.name", target = "employeeName")
    @Mapping(source = "employee.secondName", target = "employeeSecondName")
    @Mapping(source = "employee.surName", target = "employeeSurName")
    ShiftResponseDto toFullShiftDto(Shift shift);

    ShiftShortDto toShortDto(Shift shift);
    EmployeeShiftShortDto toEmployeeListDto(Shift shift);
}
