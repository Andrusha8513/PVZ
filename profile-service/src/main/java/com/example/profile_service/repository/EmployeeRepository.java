package com.example.profile_service.repository;

import com.example.profile_service.entity.Employee;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee , Long> {
    Optional<Employee> findByPhone(@Pattern(regexp = "^\\+?[1-9]\\d{1,14}$" , message = "Некорректный номер телефона!") String phone);

    boolean existsByPhoneAndPvzId(String phone, Long pvzId);
}
