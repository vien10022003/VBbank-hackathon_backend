package com.vbbankhackathon.backend.controller;

import com.vbbankhackathon.backend.entity.InsurancePolicy;
import com.vbbankhackathon.backend.repository.InsurancePolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/insurance-policies")
@CrossOrigin(originPatterns = "*")
public class InsurancePolicyController {
    
    @Autowired
    private InsurancePolicyRepository insurancePolicyRepository;
    
    @GetMapping
    public ResponseEntity<List<InsurancePolicy>> getAllInsurancePolicies() {
        List<InsurancePolicy> policies = insurancePolicyRepository.findAll();
        return ResponseEntity.ok(policies);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<InsurancePolicy> getInsurancePolicyById(@PathVariable Integer id) {
        Optional<InsurancePolicy> policy = insurancePolicyRepository.findById(id);
        return policy.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<InsurancePolicy> createInsurancePolicy(@RequestBody InsurancePolicy policy) {
        InsurancePolicy savedPolicy = insurancePolicyRepository.save(policy);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPolicy);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<InsurancePolicy> updateInsurancePolicy(@PathVariable Integer id, @RequestBody InsurancePolicy policyDetails) {
        Optional<InsurancePolicy> optionalPolicy = insurancePolicyRepository.findById(id);
        if (optionalPolicy.isPresent()) {
            InsurancePolicy policy = optionalPolicy.get();
            policy.setCustomer(policyDetails.getCustomer());
            policy.setPolicyType(policyDetails.getPolicyType());
            policy.setPremium(policyDetails.getPremium());
            policy.setCoverageAmount(policyDetails.getCoverageAmount());
            policy.setStartDate(policyDetails.getStartDate());
            policy.setEndDate(policyDetails.getEndDate());
            
            InsurancePolicy updatedPolicy = insurancePolicyRepository.save(policy);
            return ResponseEntity.ok(updatedPolicy);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInsurancePolicy(@PathVariable Integer id) {
        if (insurancePolicyRepository.existsById(id)) {
            insurancePolicyRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
