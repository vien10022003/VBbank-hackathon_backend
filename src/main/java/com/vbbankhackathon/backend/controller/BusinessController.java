package com.vbbankhackathon.backend.controller;

import com.vbbankhackathon.backend.entity.Business;
import com.vbbankhackathon.backend.repository.BusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/businesses")
@CrossOrigin(originPatterns = "*")
public class BusinessController {
    
    @Autowired
    private BusinessRepository businessRepository;
    
    @GetMapping
    public ResponseEntity<List<Business>> getAllBusinesses() {
        List<Business> businesses = businessRepository.findAll();
        return ResponseEntity.ok(businesses);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Business> getBusinessById(@PathVariable Integer id) {
        Optional<Business> business = businessRepository.findById(id);
        return business.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Business> createBusiness(@RequestBody Business business) {
        Business savedBusiness = businessRepository.save(business);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBusiness);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Business> updateBusiness(@PathVariable Integer id, @RequestBody Business businessDetails) {
        Optional<Business> optionalBusiness = businessRepository.findById(id);
        if (optionalBusiness.isPresent()) {
            Business business = optionalBusiness.get();
            business.setName(businessDetails.getName());
            business.setTaxId(businessDetails.getTaxId());
            business.setAddress(businessDetails.getAddress());
            business.setContactPerson(businessDetails.getContactPerson());
            
            Business updatedBusiness = businessRepository.save(business);
            return ResponseEntity.ok(updatedBusiness);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBusiness(@PathVariable Integer id) {
        if (businessRepository.existsById(id)) {
            businessRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
