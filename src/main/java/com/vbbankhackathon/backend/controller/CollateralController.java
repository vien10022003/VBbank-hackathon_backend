package com.vbbankhackathon.backend.controller;

import com.vbbankhackathon.backend.entity.Collateral;
import com.vbbankhackathon.backend.repository.CollateralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/collaterals")
@CrossOrigin(originPatterns = "*")
public class CollateralController {
    
    @Autowired
    private CollateralRepository collateralRepository;
    
    @GetMapping
    public ResponseEntity<List<Collateral>> getAllCollaterals() {
        List<Collateral> collaterals = collateralRepository.findAll();
        return ResponseEntity.ok(collaterals);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Collateral> getCollateralById(@PathVariable Integer id) {
        Optional<Collateral> collateral = collateralRepository.findById(id);
        return collateral.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Collateral> createCollateral(@RequestBody Collateral collateral) {
        Collateral savedCollateral = collateralRepository.save(collateral);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCollateral);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Collateral> updateCollateral(@PathVariable Integer id, @RequestBody Collateral collateralDetails) {
        Optional<Collateral> optionalCollateral = collateralRepository.findById(id);
        if (optionalCollateral.isPresent()) {
            Collateral collateral = optionalCollateral.get();
            collateral.setLoanId(collateralDetails.getLoanId());
            collateral.setType(collateralDetails.getType());
            collateral.setValue(collateralDetails.getValue());
            collateral.setDescription(collateralDetails.getDescription());
            
            Collateral updatedCollateral = collateralRepository.save(collateral);
            return ResponseEntity.ok(updatedCollateral);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCollateral(@PathVariable Integer id) {
        if (collateralRepository.existsById(id)) {
            collateralRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
