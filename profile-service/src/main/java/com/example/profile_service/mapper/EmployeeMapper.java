package com.example.profile_service.mapper;

import com.example.profile_service.dto.CreateEmployeeRequestDto;
import com.example.profile_service.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee toEntity(CreateEmployeeRequestDto createEmployeeRequestDto);
}
