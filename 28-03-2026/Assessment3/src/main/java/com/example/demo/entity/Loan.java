package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String applicantName;

    private double loanAmount;

    private String status;

    // No-arg constructor
    public Loan() {}

    // All-arg constructor
    public Loan(Long id, String applicantName, double loanAmount, String status) {
        this.id = id;
        this.applicantName = applicantName;
        this.loanAmount = loanAmount;
        this.status = status;
    }

    // Getters
    public Long getId() { return id; }
    public String getApplicantName() { return applicantName; }
    public double getLoanAmount() { return loanAmount; }
    public String getStatus() { return status; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setApplicantName(String applicantName) { this.applicantName = applicantName; }
    public void setLoanAmount(double loanAmount) { this.loanAmount = loanAmount; }
    public void setStatus(String status) { this.status = status; }
}