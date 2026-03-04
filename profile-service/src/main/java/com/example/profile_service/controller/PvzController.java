package com.example.profile_service.controller;

import com.example.profile_service.dto.CreatePvzRequestDto;
import com.example.profile_service.service.PvzService;
import com.example.support_module.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/pvz")
public class PvzController {

    private final PvzService pvzService;


    @PostMapping("/createPvz")
    @PreAuthorize("hasAuthority('ROLE_OWNER_PVZ') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> createPvz(@RequestBody CreatePvzRequestDto createPvzRequestDto,
                                       @AuthenticationPrincipal CustomUserDetails customUserDetails){
        try {
            pvzService.createPvz(createPvzRequestDto , customUserDetails.getId());
            return ResponseEntity.ok().build();
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/updateNamePvz/{id}")
    @PreAuthorize("hasAuthority('ROLE_OWNER_PVZ') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> updateNamePvz(@PathVariable Long id , @RequestParam String newName){
            try {
                pvzService.updateNamePvz(id , newName);
                return ResponseEntity.ok().build();
            }catch (RuntimeException e){
                return ResponseEntity.badRequest().body(e.getMessage());
            }
    }

    @PutMapping("/updateAddressPvz/{id}")
    @PreAuthorize("hasAuthority('ROLE_OWNER_PVZ') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> updateAddressPvz(@PathVariable Long id , @RequestParam String newName){
        try {
            pvzService.updateAddressPvz(id , newName);
            return ResponseEntity.ok().build();
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/deletePvz/{id}")
    @PreAuthorize("hasAuthority('ROLE_OWNER_PVZ') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> deletePvz(@PathVariable Long id){
        try {
            pvzService.deletePvz(id);
            return ResponseEntity.ok().build();
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
