package com.example.profile_service.repository;

import com.example.profile_service.entity.Employee;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee , Long> {
    Optional<Employee> findByPhone(@Pattern(regexp = "^\\+?[1-9]\\d{1,14}$" , message = "Некорректный номер телефона!") String phone);

    boolean existsByPhoneAndPvzId(String phone, Long pvzId);

    @Query("SELECT e FROM Employee e WHERE e.pvz.id = :pvzId AND " +
            "(:name IS NULL OR :name = '' OR LOWER(e.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:secondName IS NULL OR :secondName = '' OR LOWER(e.secondName) LIKE LOWER(CONCAT('%', :secondName, '%'))) AND " +
            "(:surName IS NULL OR :surName = '' OR LOWER(e.surName) LIKE LOWER(CONCAT('%', :surName, '%')))")
    List<Employee> searchEmployees(@Param("pvzId") Long pvzId,
                                   @Param("name") String name,
                                   @Param("secondName") String secondName,
                                   @Param("surName") String surName);


}
