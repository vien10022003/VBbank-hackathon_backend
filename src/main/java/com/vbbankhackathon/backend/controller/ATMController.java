package com.vbbankhackathon.backend.controller;

import com.vbbankhackathon.backend.entity.ATM;
import com.vbbankhackathon.backend.repository.ATMRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/atms")
@CrossOrigin(originPatterns = "*")
public class ATMController {
    
    @Autowired
    private ATMRepository atmRepository;
    
    @GetMapping
    public ResponseEntity<List<ATM>> getAllATMs() {
        List<ATM> atms = atmRepository.findAll();
        return ResponseEntity.ok(atms);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ATM> getATMById(@PathVariable Integer id) {
        Optional<ATM> atm = atmRepository.findById(id);
        return atm.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<ATM> createATM(@RequestBody ATM atm) {
        ATM savedATM = atmRepository.save(atm);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedATM);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ATM> updateATM(@PathVariable Integer id, @RequestBody ATM atmDetails) {
        Optional<ATM> optionalATM = atmRepository.findById(id);
        if (optionalATM.isPresent()) {
            ATM atm = optionalATM.get();
            atm.setLocation(atmDetails.getLocation());
            atm.setStatus(atmDetails.getStatus());
            
            ATM updatedATM = atmRepository.save(atm);
            return ResponseEntity.ok(updatedATM);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteATM(@PathVariable Integer id) {
        if (atmRepository.existsById(id)) {
            atmRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
