package com.example.profile_service.mapper;

import com.example.profile_service.dto.CreateShiftRequestDto;
import com.example.profile_service.entity.Shift;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShiftMapper {
    Shift toEntity(CreateShiftRequestDto createShiftRequestDto);
}
