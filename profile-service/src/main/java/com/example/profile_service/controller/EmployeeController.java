package com.example.profile_service.controller;

import com.example.profile_service.dto.CreateEmployeeRequestDto;
import com.example.profile_service.dto.EmployeeShortDto;
import com.example.profile_service.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("/createEmployee/{id}")
    @PreAuthorize("hasAuthority('ROLE_OWNER_PVZ') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> createEmployee(@RequestBody CreateEmployeeRequestDto createEmployeeRequestDto,
                                            @PathVariable Long id){
        try {
            employeeService.createEmployee(createEmployeeRequestDto , id);
            return ResponseEntity.ok().build();
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/deleteEmployee/{id}")
    @PreAuthorize("hasAuthority('ROLE_OWNER_PVZ') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id){
        try {
            employeeService.deleteEmployee(id);
            return ResponseEntity.ok().build();
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/addDescription/{id}")
    @PreAuthorize("hasAuthority('ROLE_OWNER_PVZ') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> addDescription(@PathVariable Long id , @RequestBody String description){
        try {
            employeeService.addDescription(id , description);
            return ResponseEntity.ok().build();
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping("/addBank/{id}")
    @PreAuthorize("hasAuthority('ROLE_OWNER_PVZ') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> addBank(@PathVariable Long id , @RequestBody String bank){
        try {
            employeeService.addBank(id , bank);
            return ResponseEntity.ok().build();
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/search/{pvzId}")
    @PreAuthorize("hasAuthority('ROLE_OWNER_PVZ') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> searchEmployees(@PathVariable Long pvzId,
                                             @RequestParam(required = false) String name,
                                             @RequestParam(required = false) String secondName,
                                             @RequestParam(required = false) String surName) {
        try {
            List<EmployeeShortDto> employees = employeeService.searchEmployees(pvzId, name, secondName, surName);
            return ResponseEntity.ok(employees);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
