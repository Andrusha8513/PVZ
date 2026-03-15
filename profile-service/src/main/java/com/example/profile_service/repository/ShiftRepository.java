package com.example.profile_service.repository;

import com.example.profile_service.entity.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShiftRepository extends JpaRepository<Shift, Long> {
    List<Shift> findShiftById(Long id);


    @Query("SELECT s FROM Shift s WHERE s.employee.pvz.id = :pvzId")
    List<Shift> findAllByPvzId(@Param("pvzId") Long pvzId);

    @Query("SELECT s FROM Shift s WHERE s.employee.id = :employeeId AND s.employee.pvz.id = :pvzId")
    List<Shift> findAllByEmployeeIdAndPvzId(@Param("employeeId") Long employeeId, @Param("pvzId") Long pvzId);
}
