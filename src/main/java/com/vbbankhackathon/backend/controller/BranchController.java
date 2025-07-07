package com.vbbankhackathon.backend.controller;

import com.vbbankhackathon.backend.entity.Branch;
import com.vbbankhackathon.backend.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/branches")
@CrossOrigin(origins = "*")
public class BranchController {
    
    @Autowired
    private BranchRepository branchRepository;
    
    @GetMapping
    public ResponseEntity<List<Branch>> getAllBranches() {
        List<Branch> branches = branchRepository.findAll();
        return ResponseEntity.ok(branches);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Branch> getBranchById(@PathVariable Integer id) {
        Optional<Branch> branch = branchRepository.findById(id);
        return branch.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Branch> createBranch(@RequestBody Branch branch) {
        Branch savedBranch = branchRepository.save(branch);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBranch);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Branch> updateBranch(@PathVariable Integer id, @RequestBody Branch branchDetails) {
        Optional<Branch> optionalBranch = branchRepository.findById(id);
        if (optionalBranch.isPresent()) {
            Branch branch = optionalBranch.get();
            branch.setAddress(branchDetails.getAddress());
            branch.setManagerId(branchDetails.getManagerId());
            branch.setPhoneNumber(branchDetails.getPhoneNumber());
            
            Branch updatedBranch = branchRepository.save(branch);
            return ResponseEntity.ok(updatedBranch);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBranch(@PathVariable Integer id) {
        if (branchRepository.existsById(id)) {
            branchRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
