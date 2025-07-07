package com.vbbankhackathon.backend.controller;

import com.vbbankhackathon.backend.entity.AMLCheck;
import com.vbbankhackathon.backend.repository.AMLCheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/aml-checks")
@CrossOrigin(originPatterns = "*")
public class AMLCheckController {
    
    @Autowired
    private AMLCheckRepository amlCheckRepository;
    
    @GetMapping
    public ResponseEntity<List<AMLCheck>> getAllAMLChecks() {
        List<AMLCheck> amlChecks = amlCheckRepository.findAll();
        return ResponseEntity.ok(amlChecks);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<AMLCheck> getAMLCheckById(@PathVariable Integer id) {
        Optional<AMLCheck> amlCheck = amlCheckRepository.findById(id);
        return amlCheck.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<AMLCheck> createAMLCheck(@RequestBody AMLCheck amlCheck) {
        AMLCheck savedAMLCheck = amlCheckRepository.save(amlCheck);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAMLCheck);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<AMLCheck> updateAMLCheck(@PathVariable Integer id, @RequestBody AMLCheck amlCheckDetails) {
        Optional<AMLCheck> optionalAMLCheck = amlCheckRepository.findById(id);
        if (optionalAMLCheck.isPresent()) {
            AMLCheck amlCheck = optionalAMLCheck.get();
            amlCheck.setTransaction(amlCheckDetails.getTransaction());
            amlCheck.setCheckDate(amlCheckDetails.getCheckDate());
            amlCheck.setResult(amlCheckDetails.getResult());
            
            AMLCheck updatedAMLCheck = amlCheckRepository.save(amlCheck);
            return ResponseEntity.ok(updatedAMLCheck);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAMLCheck(@PathVariable Integer id) {
        if (amlCheckRepository.existsById(id)) {
            amlCheckRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
