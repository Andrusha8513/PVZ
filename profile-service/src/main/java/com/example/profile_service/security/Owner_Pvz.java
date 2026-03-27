package com.example.profile_service.security;

import com.example.profile_service.entity.Pvz;
import com.example.support_module.security.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class Owner_Pvz {
    public void checkAccess(Pvz pvz) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long currentUserId = userDetails.getId();

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin && !pvz.getOwner().getId().equals(currentUserId)) {
            throw new RuntimeException("Отказано в доступе! Вы не являетесь владельцем этого ПВЗ.");
        }
    }

}
