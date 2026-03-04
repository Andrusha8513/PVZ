package com.example.profile_service.service;

import com.example.profile_service.dto.CreatePvzRequestDto;;
import com.example.profile_service.entity.Profile;
import com.example.profile_service.entity.Pvz;
import com.example.profile_service.mapper.PvzMapper;


import com.example.profile_service.repository.ProfileRepository;
import com.example.profile_service.repository.PvzRepository;
import com.example.support_module.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class PvzService {
    private final PvzRepository pvzRepository;
    private final PvzMapper pvzMapper;
    private final ProfileRepository profileRepository;

    public void createPvz(CreatePvzRequestDto createPvzRequestDto , Long ownerId) {
        if (pvzRepository.findByAddress(createPvzRequestDto.address()).isPresent()) {
            throw new IllegalArgumentException("ПВЗ с таким адресом  уже существует");
        }
        Profile owner = profileRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Профиль с id " + ownerId + " не найден"));
      Pvz pvz = pvzMapper.toEntity(createPvzRequestDto);
      pvz.setOwner(owner);
      pvzRepository.save(pvz);
    }

    public void updateNamePvz(Long id , String newName){
        Pvz pvz = pvzRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Пвз не найден"));

        pvz.setName(newName);
        pvzRepository.save(pvz);
    }

    public void updateAddressPvz(Long id , String newAddress){
        Pvz pvz = pvzRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Пвз не найден"));

        pvz.setAddress(newAddress);
        pvzRepository.save(pvz);
    }

    public void deletePvz(Long id){
        Pvz pvz = pvzRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Пвз не найден"));
        pvzRepository.delete(pvz);
    }
}
