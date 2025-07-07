package com.vbbankhackathon.backend.controller;

import com.vbbankhackathon.backend.entity.DebtCollection;
import com.vbbankhackathon.backend.repository.DebtCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/debt-collections")
@CrossOrigin(originPatterns = "*")
public class DebtCollectionController {
    
    @Autowired
    private DebtCollectionRepository debtCollectionRepository;
    
    @GetMapping
    public ResponseEntity<List<DebtCollection>> getAllDebtCollections() {
        List<DebtCollection> debtCollections = debtCollectionRepository.findAll();
        return ResponseEntity.ok(debtCollections);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<DebtCollection> getDebtCollectionById(@PathVariable Integer id) {
        Optional<DebtCollection> debtCollection = debtCollectionRepository.findById(id);
        return debtCollection.map(ResponseEntity::ok)
                            .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<DebtCollection> createDebtCollection(@RequestBody DebtCollection debtCollection) {
        DebtCollection savedDebtCollection = debtCollectionRepository.save(debtCollection);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDebtCollection);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<DebtCollection> updateDebtCollection(@PathVariable Integer id, @RequestBody DebtCollection debtCollectionDetails) {
        Optional<DebtCollection> optionalDebtCollection = debtCollectionRepository.findById(id);
        if (optionalDebtCollection.isPresent()) {
            DebtCollection debtCollection = optionalDebtCollection.get();
            debtCollection.setLoan(debtCollectionDetails.getLoan());
            debtCollection.setAmountDue(debtCollectionDetails.getAmountDue());
            debtCollection.setDueDate(debtCollectionDetails.getDueDate());
            debtCollection.setStatus(debtCollectionDetails.getStatus());
            
            DebtCollection updatedDebtCollection = debtCollectionRepository.save(debtCollection);
            return ResponseEntity.ok(updatedDebtCollection);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDebtCollection(@PathVariable Integer id) {
        if (debtCollectionRepository.existsById(id)) {
            debtCollectionRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
