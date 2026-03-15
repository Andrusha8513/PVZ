package com.example.profile_service.mapper;

import com.example.profile_service.dto.CreateEmployeeRequestDto;
import com.example.profile_service.dto.EmployeeShiftShortDto;
import com.example.profile_service.dto.EmployeeShortDto;
import com.example.profile_service.entity.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee toEntity(CreateEmployeeRequestDto createEmployeeRequestDto);
    EmployeeShortDto toShortDto(Employee employee);
}
