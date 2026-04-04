package com.example.demo.dto;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class EmpDto {

    private Integer empId;

    @NotBlank(message = "Employee name is required")
    @Pattern(regexp = "^[a-zA-Z ]{3,25}$",
             message = "Name must contain only alphabets with min 3 and max 25 characters")
    private String empName;

    @NotNull(message = "Salary is required")
    @Min(value = 1000, message = "Salary must be at least Rs 1000")
    @Max(value = 500000, message = "Salary must not exceed Rs 500000")
    private Double empSal;

    @NotNull(message = "Date of joining is required")
    @FutureOrPresent(message = "Date of joining must be current or a future date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate empDoj;

    @NotBlank(message = "Department name is required")
    @Pattern(regexp = "^(hr|production)$",
             message = "Department must be 'hr' or 'production'")
    private String deptName;

    // Constructors
    public EmpDto() {}

    public EmpDto(Integer empId, String empName, Double empSal, LocalDate empDoj, String deptName) {
        this.empId = empId;
        this.empName = empName;
        this.empSal = empSal;
        this.empDoj = empDoj;
        this.deptName = deptName;
    }

    // Getters and Setters
    public Integer getEmpId() { return empId; }
    public void setEmpId(Integer empId) { this.empId = empId; }

    public String getEmpName() { return empName; }
    public void setEmpName(String empName) { this.empName = empName; }

    public Double getEmpSal() { return empSal; }
    public void setEmpSal(Double empSal) { this.empSal = empSal; }

    public LocalDate getEmpDoj() { return empDoj; }
    public void setEmpDoj(LocalDate empDoj) { this.empDoj = empDoj; }

    public String getDeptName() { return deptName; }
    public void setDeptName(String deptName) { this.deptName = deptName; }
}
