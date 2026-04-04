package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "emp")
public class Emp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer empId;

    private String empName;
    private Double empSal;
    private LocalDate empDoj;
    private String deptName;

    // Constructors
    public Emp() {}

    public Emp(Integer empId, String empName, Double empSal, LocalDate empDoj, String deptName) {
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
