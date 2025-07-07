package com.vbbankhackathon.backend.controller;

import com.vbbankhackathon.backend.entity.CreditScore;
import com.vbbankhackathon.backend.repository.CreditScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/credit-scores")
@CrossOrigin(origins = "*")
public class CreditScoreController {
    
    @Autowired
    private CreditScoreRepository creditScoreRepository;
    
    @GetMapping
    public ResponseEntity<List<CreditScore>> getAllCreditScores() {
        List<CreditScore> creditScores = creditScoreRepository.findAll();
        return ResponseEntity.ok(creditScores);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CreditScore> getCreditScoreById(@PathVariable Integer id) {
        Optional<CreditScore> creditScore = creditScoreRepository.findById(id);
        return creditScore.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<CreditScore> createCreditScore(@RequestBody CreditScore creditScore) {
        CreditScore savedCreditScore = creditScoreRepository.save(creditScore);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCreditScore);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CreditScore> updateCreditScore(@PathVariable Integer id, @RequestBody CreditScore creditScoreDetails) {
        Optional<CreditScore> optionalCreditScore = creditScoreRepository.findById(id);
        if (optionalCreditScore.isPresent()) {
            CreditScore creditScore = optionalCreditScore.get();
            creditScore.setCustomer(creditScoreDetails.getCustomer());
            creditScore.setScore(creditScoreDetails.getScore());
            creditScore.setDate(creditScoreDetails.getDate());
            
            CreditScore updatedCreditScore = creditScoreRepository.save(creditScore);
            return ResponseEntity.ok(updatedCreditScore);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCreditScore(@PathVariable Integer id) {
        if (creditScoreRepository.existsById(id)) {
            creditScoreRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
