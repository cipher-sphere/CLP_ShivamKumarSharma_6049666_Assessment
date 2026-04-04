package com.example.demo.service;

import com.example.demo.dto.EmpDto;
import com.example.demo.entity.Emp;
import com.example.demo.repository.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpRepository empRepository;

    // ---- Mapping Helpers ----

    private EmpDto toDto(Emp emp) {
        return new EmpDto(
                emp.getEmpId(),
                emp.getEmpName(),
                emp.getEmpSal(),
                emp.getEmpDoj(),
                emp.getDeptName()
        );
    }

    private Emp toEntity(EmpDto dto) {
        return new Emp(
                dto.getEmpId(),
                dto.getEmpName(),
                dto.getEmpSal(),
                dto.getEmpDoj(),
                dto.getDeptName()
        );
    }

    // ---- Service Methods ----

    @Override
    public List<EmpDto> getAllEmployees() {
        return empRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmpDto getEmployeeById(Integer id) {
        Emp emp = empRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        return toDto(emp);
    }

    @Override
    public void updateEmployee(EmpDto empDto) {
        // Ensure employee exists before updating
        empRepository.findById(empDto.getEmpId())
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + empDto.getEmpId()));
        empRepository.save(toEntity(empDto));
    }

    @Override
    public void deleteEmployee(Integer id) {
        empRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        empRepository.deleteById(id);
    }
}
