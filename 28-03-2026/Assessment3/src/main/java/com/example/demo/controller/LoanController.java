package com.example.demo.controller;

import com.example.demo.entity.Loan;
import com.example.demo.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    // POST /loans - Submit a new loan request
    @PostMapping
    public ResponseEntity<Loan> applyLoan(@RequestBody Loan loan) {
        Loan savedLoan = loanService.applyLoan(loan);
        return new ResponseEntity<>(savedLoan, HttpStatus.CREATED);
    }

    // GET /loans - View all loan applications
    @GetMapping
    public ResponseEntity<List<Loan>> getAllLoans() {
        return ResponseEntity.ok(loanService.getAllLoans());
        
    }

    // GET /loans/{id} - View a specific loan by ID
    @GetMapping("/{id}")
    public ResponseEntity<Loan> getLoanById(@PathVariable Long id) {
        return ResponseEntity.ok(loanService.getLoanById(id));
    }

    // PUT /loans/{id}/status - Update loan status
    @PutMapping("/{id}/status")
    public ResponseEntity<Loan> updateStatus(@PathVariable Long id,
                                              @RequestBody Map<String, String> body) {
        String status = body.get("status");
        return ResponseEntity.ok(loanService.updateLoanStatus(id, status));
    }
}
