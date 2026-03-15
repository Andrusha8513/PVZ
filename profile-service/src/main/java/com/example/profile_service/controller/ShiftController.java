package com.example.profile_service.controller;

import com.example.profile_service.dto.CreateShiftRequestDto;
import com.example.profile_service.dto.EmployeeShiftShortDto;
import com.example.profile_service.dto.ShiftResponseDto;
import com.example.profile_service.dto.ShiftShortDto;
import com.example.profile_service.entity.Shift;
import com.example.profile_service.entity.ShiftStatus;
import com.example.profile_service.service.ShiftService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/shift")
public class ShiftController {

    private static final Log log = LogFactory.getLog(ShiftController.class);
    private final ShiftService shiftService;

    @PostMapping("/createShift")
    @PreAuthorize("hasAuthority('ROLE_OWNER_PVZ') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> createShift(@RequestBody CreateShiftRequestDto createShiftRequestDto) {
        try {
            shiftService.createShift(createShiftRequestDto);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/addBonus/{id}")
    @PreAuthorize("hasAuthority('ROLE_OWNER_PVZ') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> addBonus(@PathVariable Long id,
                                      @RequestBody BigDecimal bonus) {
        try {
            shiftService.addBonus(id, bonus);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/addPenalty/{id}")
    @PreAuthorize("hasAuthority('ROLE_OWNER_PVZ') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> addPenalty(@PathVariable Long id,
                                        @RequestBody BigDecimal penalty) {
        try {
            shiftService.addPenalty(id, penalty);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/addPenaltyReason/{id}")
    @PreAuthorize("hasAuthority('ROLE_OWNER_PVZ') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> addPenaltyReason(@PathVariable Long id,
                                              @RequestBody String reason) {
        try {
            shiftService.addPenaltyReason(id, reason);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PutMapping("/updateStatus/{id}")
    @PreAuthorize("hasAuthority('ROLE_OWNER_PVZ') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> updateShiftStatus(@PathVariable Long id,
                                               @RequestBody ShiftStatus shiftStatus) {
        try {
            shiftService.updateShiftStatus(id, shiftStatus);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping("/actualStartTime/{id}")
    @PreAuthorize("hasAuthority('ROLE_OWNER_PVZ') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> addActualStartTime(@PathVariable Long id,
                                                @RequestBody LocalDateTime localDateTime) {
        try {
            shiftService.addActualStartTime(id, localDateTime);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/actualEndTime/{id}")
    @PreAuthorize("hasAuthority('ROLE_OWNER_PVZ') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> addActualEndTime(@PathVariable Long id,
                                              @RequestBody LocalDateTime localDateTime) {
        try {
            shiftService.addActualEndStartTime(id, localDateTime);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/calculatePay/{id}")
    @PreAuthorize("hasAuthority('ROLE_OWNER_PVZ') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> calculateShiftPay(@PathVariable Long id) {
        try {
            shiftService.calculateShiftPay(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/updateEmployeeForShift/{shiftId}/{employeeId}")
    public ResponseEntity<?> updateEmployeeForShift(@PathVariable Long shiftId ,
                                                    @PathVariable Long employeeId){
        try {
            shiftService.updateEmployeeForShift(shiftId , employeeId);
            return ResponseEntity.ok("сменили сотрудника на смене ");
        }catch (RuntimeException e){
            log.info("Критическая ошибка " + e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getFullShift/{shiftId}")
    @PreAuthorize("hasAuthority('ROLE_OWNER_PVZ') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ShiftResponseDto> getFullShift(@PathVariable Long shiftId){
        try {
            ShiftResponseDto responseDto = shiftService.getFullShift(shiftId);
            return ResponseEntity.ok(responseDto);
        }catch (RuntimeException e){
            log.error("Ошибка при получении смены: ", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/getShiftsByPvz/{pvzId}")
    @PreAuthorize("hasAuthority('ROLE_OWNER_PVZ') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<ShiftShortDto>> getShiftsByPvz(@PathVariable Long pvzId) {
        try {
            List<ShiftShortDto> shortDtos = shiftService.getShiftsByPvz(pvzId);
            return ResponseEntity.ok(shortDtos);
        } catch (RuntimeException e) {
            log.error("Ошибка при получении списка смен для ПВЗ: ", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/employee/{employeeId}/pvz/{pvzId}")
    @PreAuthorize("hasAuthority('ROLE_OWNER_PVZ') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<EmployeeShiftShortDto>> getEmployeeShiftsInPvz(
            @PathVariable Long employeeId,
            @PathVariable Long pvzId) {

        try {
            List<EmployeeShiftShortDto> employeeShiftShortDtos = shiftService.getShiftsByEmployeeAndPvz(employeeId, pvzId);
            return ResponseEntity.ok(employeeShiftShortDtos);
        } catch (RuntimeException e) {
            log.error("Ошибка при получении смен сотрудника ID " + employeeId + " для ПВЗ " + pvzId + ": ", e);
            return ResponseEntity.badRequest().build();
        }
    }
}

