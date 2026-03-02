package com.example.profile_service.service;

import com.example.profile_service.dto.CreateShiftRequestDto;
import com.example.profile_service.entity.Employee;
import com.example.profile_service.entity.Shift;
import com.example.profile_service.mapper.ShiftMapper;
import com.example.profile_service.repository.EmployeeRepository;
import com.example.profile_service.repository.ShiftRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ShiftService {
    private final ShiftRepository shiftRepository;
    private final ShiftMapper shiftMapper;
    private final EmployeeRepository employeeRepository;

    public void createShift(CreateShiftRequestDto createShiftRequestDto){
        Employee employee = employeeRepository.findById(createShiftRequestDto.employeeId())
                .orElseThrow(() -> new IllegalArgumentException("Сотрудник с id " + createShiftRequestDto.employeeId() + " не найден"));
        Shift shift = shiftMapper.toEntity(createShiftRequestDto);
        shift.setEmployee(employee);
        shiftRepository.save(shift);
    }
}
