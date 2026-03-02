package com.example.profile_service.service;

import com.example.profile_service.dto.CreateEmployeeRequestDto;
import com.example.profile_service.entity.Employee;
import com.example.profile_service.entity.Pvz;
import com.example.profile_service.mapper.EmployeeMapper;
import com.example.profile_service.repository.EmployeeRepository;
import com.example.profile_service.repository.PvzRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final PvzRepository pvzRepository;

    @Transactional
    public void createEmployee(CreateEmployeeRequestDto createEmployeeRequestDto , Long pvzId){
        if (employeeRepository.findByPhone(createEmployeeRequestDto.phone()).isPresent()) {
            throw new IllegalArgumentException("Сотрудник с таким телефоном уже существует");
        }
        Employee employee = employeeMapper.toEntity(createEmployeeRequestDto);
        Pvz pvz = pvzRepository.findById(pvzId)
                        .orElseThrow(() -> new IllegalArgumentException("Пвз не найдено"));
        employee.setPvz(pvz);
        pvz.getEmployees().add(employee);
        employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Сотрудник не найден"));
        employeeRepository.delete(employee);
    }

}
