package com.vbbankhackathon.backend.controller;

import com.vbbankhackathon.backend.entity.EWallet;
import com.vbbankhackathon.backend.repository.EWalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ewallets")
@CrossOrigin(origins = "*")
public class EWalletController {
    
    @Autowired
    private EWalletRepository eWalletRepository;
    
    @GetMapping
    public ResponseEntity<List<EWallet>> getAllEWallets() {
        List<EWallet> eWallets = eWalletRepository.findAll();
        return ResponseEntity.ok(eWallets);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<EWallet> getEWalletById(@PathVariable Integer id) {
        Optional<EWallet> eWallet = eWalletRepository.findById(id);
        return eWallet.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<EWallet> createEWallet(@RequestBody EWallet eWallet) {
        EWallet savedEWallet = eWalletRepository.save(eWallet);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEWallet);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<EWallet> updateEWallet(@PathVariable Integer id, @RequestBody EWallet eWalletDetails) {
        Optional<EWallet> optionalEWallet = eWalletRepository.findById(id);
        if (optionalEWallet.isPresent()) {
            EWallet eWallet = optionalEWallet.get();
            // Note: EWallet class needs getters and setters to be added
            
            EWallet updatedEWallet = eWalletRepository.save(eWallet);
            return ResponseEntity.ok(updatedEWallet);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEWallet(@PathVariable Integer id) {
        if (eWalletRepository.existsById(id)) {
            eWalletRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
