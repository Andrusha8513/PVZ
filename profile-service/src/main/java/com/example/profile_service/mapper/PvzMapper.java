package com.example.profile_service.mapper;

import com.example.profile_service.dto.CreatePvzRequestDto;
import com.example.profile_service.entity.Pvz;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PvzMapper {
    Pvz toEntity(CreatePvzRequestDto createPvzRequestDto);
}
