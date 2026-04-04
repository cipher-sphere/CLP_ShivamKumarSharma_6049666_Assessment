package com.example.demo.service;

import com.example.demo.dto.EmpDto;

import java.util.List;

public interface EmpService {
    List<EmpDto> getAllEmployees();
    EmpDto getEmployeeById(Integer id);
    void updateEmployee(EmpDto empDto);
    void deleteEmployee(Integer id);
}
