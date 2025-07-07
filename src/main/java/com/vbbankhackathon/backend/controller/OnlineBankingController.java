package com.vbbankhackathon.backend.controller;

import com.vbbankhackathon.backend.entity.OnlineBanking;
import com.vbbankhackathon.backend.repository.OnlineBankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/online-banking")
@CrossOrigin(origins = "*")
public class OnlineBankingController {
    
    @Autowired
    private OnlineBankingRepository onlineBankingRepository;
    
    @GetMapping
    public ResponseEntity<List<OnlineBanking>> getAllOnlineBanking() {
        List<OnlineBanking> onlineBankings = onlineBankingRepository.findAll();
        return ResponseEntity.ok(onlineBankings);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<OnlineBanking> getOnlineBankingById(@PathVariable Integer id) {
        Optional<OnlineBanking> onlineBanking = onlineBankingRepository.findById(id);
        return onlineBanking.map(ResponseEntity::ok)
                           .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<OnlineBanking> createOnlineBanking(@RequestBody OnlineBanking onlineBanking) {
        OnlineBanking savedOnlineBanking = onlineBankingRepository.save(onlineBanking);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOnlineBanking);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<OnlineBanking> updateOnlineBanking(@PathVariable Integer id, @RequestBody OnlineBanking onlineBankingDetails) {
        Optional<OnlineBanking> optionalOnlineBanking = onlineBankingRepository.findById(id);
        if (optionalOnlineBanking.isPresent()) {
            OnlineBanking onlineBanking = optionalOnlineBanking.get();
            onlineBanking.setCustomerId(onlineBankingDetails.getCustomerId());
            onlineBanking.setUsername(onlineBankingDetails.getUsername());
            onlineBanking.setPassword(onlineBankingDetails.getPassword());
            onlineBanking.setLastLogin(onlineBankingDetails.getLastLogin());
            
            OnlineBanking updatedOnlineBanking = onlineBankingRepository.save(onlineBanking);
            return ResponseEntity.ok(updatedOnlineBanking);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnlineBanking(@PathVariable Integer id) {
        if (onlineBankingRepository.existsById(id)) {
            onlineBankingRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
