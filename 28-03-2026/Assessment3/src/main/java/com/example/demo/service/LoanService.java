package com.example.demo.service;

import com.example.demo.entity.Loan;
import java.util.List;

public interface LoanService {
    Loan applyLoan(Loan loan);
    List<Loan> getAllLoans();
    Loan getLoanById(Long id);
    Loan updateLoanStatus(Long id, String status);
}