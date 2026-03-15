package com.example.profile_service.service;

import com.example.profile_service.dto.CreateEmployeeRequestDto;
import com.example.profile_service.dto.EmployeeShortDto;
import com.example.profile_service.entity.Employee;
import com.example.profile_service.entity.Pvz;
import com.example.profile_service.mapper.EmployeeMapper;
import com.example.profile_service.repository.EmployeeRepository;
import com.example.profile_service.repository.PvzRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RequiredArgsConstructor
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final PvzRepository pvzRepository;

    @Transactional
    public void createEmployee(CreateEmployeeRequestDto createEmployeeRequestDto , Long pvzId){
        if (employeeRepository.existsByPhoneAndPvzId(createEmployeeRequestDto.phone(), pvzId)){
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


    public void addDescription(Long id , String description){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Сотрудник не найден"));
        employee.setDescription(description);
        employeeRepository.delete(employee);
    }

    public void addBank(Long id , String bank){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Сотрудник не найден"));
        employee.setBank(bank);
        employeeRepository.delete(employee);
    }

    @Transactional(readOnly = true)
    public List<EmployeeShortDto> searchEmployees(Long pvzId, String name, String secondName, String surName) {
        System.out.println("Поиск: pvzId=" + pvzId + ", name=" + name + ", secondName=" + secondName + ", surName=" + surName);
        List<Employee> result = employeeRepository.searchEmployees(pvzId, name, secondName, surName);
        System.out.println("Найдено сотрудников: " + result.size());
       List <EmployeeShortDto> shortDtos =  result.stream()
                .map(employeeMapper::toShortDto)
                .toList();
        return shortDtos;
    }
}
