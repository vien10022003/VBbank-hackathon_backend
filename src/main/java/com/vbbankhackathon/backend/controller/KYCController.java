package com.vbbankhackathon.backend.controller;

import com.vbbankhackathon.backend.entity.KYC;
import com.vbbankhackathon.backend.repository.KYCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/kyc")
@CrossOrigin(originPatterns = "*")
public class KYCController {
    
    @Autowired
    private KYCRepository kycRepository;
    
    @GetMapping
    public ResponseEntity<List<KYC>> getAllKYC() {
        List<KYC> kycs = kycRepository.findAll();
        return ResponseEntity.ok(kycs);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<KYC> getKYCById(@PathVariable Integer id) {
        Optional<KYC> kyc = kycRepository.findById(id);
        return kyc.map(ResponseEntity::ok)
                 .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<KYC> createKYC(@RequestBody KYC kyc) {
        KYC savedKYC = kycRepository.save(kyc);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedKYC);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<KYC> updateKYC(@PathVariable Integer id, @RequestBody KYC kycDetails) {
        Optional<KYC> optionalKYC = kycRepository.findById(id);
        if (optionalKYC.isPresent()) {
            KYC kyc = optionalKYC.get();
            kyc.setCustomer(kycDetails.getCustomer());
            kyc.setDocumentType(kycDetails.getDocumentType());
            kyc.setDocumentNumber(kycDetails.getDocumentNumber());
            kyc.setIssueDate(kycDetails.getIssueDate());
            kyc.setExpiryDate(kycDetails.getExpiryDate());
            
            KYC updatedKYC = kycRepository.save(kyc);
            return ResponseEntity.ok(updatedKYC);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKYC(@PathVariable Integer id) {
        if (kycRepository.existsById(id)) {
            kycRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
