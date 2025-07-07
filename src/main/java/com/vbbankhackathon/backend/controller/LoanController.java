package com.vbbankhackathon.backend.controller;

import com.vbbankhackathon.backend.entity.Loan;
import com.vbbankhackathon.backend.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/loans")
@CrossOrigin(originPatterns = "*")
public class LoanController {
    
    @Autowired
    private LoanRepository loanRepository;
    
    @GetMapping
    public ResponseEntity<List<Loan>> getAllLoans() {
        List<Loan> loans = loanRepository.findAll();
        return ResponseEntity.ok(loans);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Loan> getLoanById(@PathVariable Integer id) {
        Optional<Loan> loan = loanRepository.findById(id);
        return loan.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Loan> createLoan(@RequestBody Loan loan) {
        Loan savedLoan = loanRepository.save(loan);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedLoan);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Loan> updateLoan(@PathVariable Integer id, @RequestBody Loan loanDetails) {
        Optional<Loan> optionalLoan = loanRepository.findById(id);
        if (optionalLoan.isPresent()) {
            Loan loan = optionalLoan.get();
            loan.setCustomerId(loanDetails.getCustomerId());
            loan.setLoanAmount(loanDetails.getLoanAmount());
            loan.setInterestRate(loanDetails.getInterestRate());
            loan.setTerm(loanDetails.getTerm());
            loan.setStartDate(loanDetails.getStartDate());
            loan.setEndDate(loanDetails.getEndDate());
            loan.setStatus(loanDetails.getStatus());
            loan.setMonthlyPayment(loanDetails.getMonthlyPayment());
            loan.setLoanPurpose(loanDetails.getLoanPurpose());
            loan.setRepaymentSchedule(loanDetails.getRepaymentSchedule());
            
            Loan updatedLoan = loanRepository.save(loan);
            return ResponseEntity.ok(updatedLoan);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoan(@PathVariable Integer id) {
        if (loanRepository.existsById(id)) {
            loanRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
