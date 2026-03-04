package com.example.profile_service.mapper;

import com.example.profile_service.dto.ProfileDashboardResponseDto;
import com.example.profile_service.entity.Profile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileDashboardMapper {
    ProfileDashboardResponseDto toDto(Profile profile);
}
