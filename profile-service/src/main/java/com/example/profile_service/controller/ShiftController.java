package com.example.profile_service.controller;

import com.example.profile_service.dto.CreateShiftRequestDto;
import com.example.profile_service.service.ShiftService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/shift")
public class ShiftController {

    private final ShiftService shiftService;

    @PostMapping("/createShift")
    public ResponseEntity<?> createShift(@RequestBody CreateShiftRequestDto createShiftRequestDto){
        try {
            shiftService.createShift(createShiftRequestDto);
            return ResponseEntity.ok().build();
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
