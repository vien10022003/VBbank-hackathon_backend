package com.vbbankhackathon.backend.controller;

import com.vbbankhackathon.backend.entity.RiskAssessment;
import com.vbbankhackathon.backend.repository.RiskAssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/risk-assessments")
@CrossOrigin(originPatterns = "*")
public class RiskAssessmentController {
    
    @Autowired
    private RiskAssessmentRepository riskAssessmentRepository;
    
    @GetMapping
    public ResponseEntity<List<RiskAssessment>> getAllRiskAssessments() {
        List<RiskAssessment> riskAssessments = riskAssessmentRepository.findAll();
        return ResponseEntity.ok(riskAssessments);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<RiskAssessment> getRiskAssessmentById(@PathVariable Integer id) {
        Optional<RiskAssessment> riskAssessment = riskAssessmentRepository.findById(id);
        return riskAssessment.map(ResponseEntity::ok)
                           .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<RiskAssessment> createRiskAssessment(@RequestBody RiskAssessment riskAssessment) {
        RiskAssessment savedRiskAssessment = riskAssessmentRepository.save(riskAssessment);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRiskAssessment);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<RiskAssessment> updateRiskAssessment(@PathVariable Integer id, @RequestBody RiskAssessment riskAssessmentDetails) {
        Optional<RiskAssessment> optionalRiskAssessment = riskAssessmentRepository.findById(id);
        if (optionalRiskAssessment.isPresent()) {
            RiskAssessment riskAssessment = optionalRiskAssessment.get();
            riskAssessment.setRiskLevel(riskAssessmentDetails.getRiskLevel());
            riskAssessment.setAssessmentDate(riskAssessmentDetails.getAssessmentDate());
            riskAssessment.setCustomer(riskAssessmentDetails.getCustomer());
            
            RiskAssessment updatedRiskAssessment = riskAssessmentRepository.save(riskAssessment);
            return ResponseEntity.ok(updatedRiskAssessment);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRiskAssessment(@PathVariable Integer id) {
        if (riskAssessmentRepository.existsById(id)) {
            riskAssessmentRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
