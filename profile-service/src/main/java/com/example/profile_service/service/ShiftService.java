package com.example.profile_service.service;

import com.example.profile_service.dto.CreateShiftRequestDto;
import com.example.profile_service.entity.Employee;
import com.example.profile_service.entity.Shift;
import com.example.profile_service.entity.ShiftStatus;
import com.example.profile_service.mapper.ShiftMapper;
import com.example.profile_service.repository.EmployeeRepository;
import com.example.profile_service.repository.ShiftRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@RequiredArgsConstructor
@Service
public class ShiftService {
    private final ShiftRepository shiftRepository;
    private final ShiftMapper shiftMapper;
    private final EmployeeRepository employeeRepository;

    public void createShift(CreateShiftRequestDto createShiftRequestDto) {
        Employee employee = employeeRepository.findById(createShiftRequestDto.employeeId())
                .orElseThrow(() -> new IllegalArgumentException("Сотрудник с id " + createShiftRequestDto.employeeId() + " не найден"));
        Shift shift = shiftMapper.toEntity(createShiftRequestDto);
        shift.setEmployee(employee);
        shift.setShiftStatus(ShiftStatus.PLANNED);
        shiftRepository.save(shift);
    }


    public void updateShiftStatus(Long id, ShiftStatus shiftStatus) {
        Shift shift = shiftRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Смена не найдена"));
        shift.setShiftStatus(shiftStatus);
        shiftRepository.save(shift);
    }


    public void addActualStartTime(Long id, LocalDateTime localDateTime) {
        Shift shift = shiftRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Смена не найдена"));
        shift.setActualStartTime(localDateTime);
        shiftRepository.save(shift);
    }

    public void addActualEndStartTime(Long id, LocalDateTime localDateTime) {
        Shift shift = shiftRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Смена не найдена"));
        shift.setActualEndTime(localDateTime);
        shiftRepository.save(shift);
    }

    public void addBonus(Long id, BigDecimal bonus) {
        Shift shift = shiftRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Смена не найдена"));

        shift.setBonus(bonus);
        shiftRepository.save(shift);
    }


    public void addPenalty(Long id, BigDecimal penalty) {
        Shift shift = shiftRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Смена не найдена"));

        shift.setPenalty(penalty);
        shiftRepository.save(shift);
    }

    public void addPenaltyReason(Long id, String penaltyReason) {
        Shift shift = shiftRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Смена не найдена"));

        shift.setPenaltyReason(penaltyReason);
        shiftRepository.save(shift);
    }

    public void updateEmployeeForShift(Long shiftId , Long employeeId){
        Shift shift = shiftRepository.findById(shiftId)
                .orElseThrow(() -> new IllegalArgumentException("Смена не найдена"));

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("Сотрудник  не найден"));
        shift.setEmployee(employee);
        shiftRepository.save(shift);
    }

    public void calculateShiftPay(Long shiftId) {
        Shift shift = shiftRepository.findById(shiftId)
                .orElseThrow(() -> new IllegalArgumentException("Смена с id " + shiftId + " не найдена"));

        if (shift.getActualStartTime() == null || shift.getActualEndTime() == null) {
            throw new IllegalStateException("Для расчёта необходимо установить фактическое время начала и окончания смены");
        }

        if (shift.getActualEndTime().isBefore(shift.getActualStartTime())) {
            throw new IllegalStateException("Время окончания не может быть раньше времени начала");
        }

        Employee employee = shift.getEmployee();
        if (employee == null) {
            throw new IllegalStateException("Смена не привязана к сотруднику");
        }

        BigDecimal rate = employee.getFixedRatePerHour();
        if (rate == null) {
            throw new IllegalStateException("У сотрудника не указана ставка за час");
        }

        long minutes = Duration.between(shift.getActualStartTime(), shift.getActualEndTime()).toMinutes();
        if (minutes < 0) {
            throw new IllegalStateException("Отработанное время должно быть положительным");
        }

        BigDecimal hours = BigDecimal.valueOf(minutes)
                .divide(BigDecimal.valueOf(60), 2, RoundingMode.HALF_UP);

        BigDecimal basaPay = rate.multiply(hours);

        BigDecimal bonus = shift.getBonus() != null ? shift.getBonus() : BigDecimal.ZERO;
        BigDecimal penalty = shift.getPenalty() != null ? shift.getPenalty() : BigDecimal.ZERO;
        BigDecimal employeeSalary = basaPay.add(bonus).subtract(penalty);
        shift.setEmployeeSalary(employeeSalary);
        shiftRepository.save(shift);
    }

}
