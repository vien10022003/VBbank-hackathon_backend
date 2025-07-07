package com.vbbankhackathon.backend.controller;

import com.vbbankhackathon.backend.entity.InterbankTransfer;
import com.vbbankhackathon.backend.repository.InterbankTransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/interbank-transfers")
@CrossOrigin(originPatterns = "*")
public class InterbankTransferController {
    
    @Autowired
    private InterbankTransferRepository interbankTransferRepository;
    
    @GetMapping
    public ResponseEntity<List<InterbankTransfer>> getAllInterbankTransfers() {
        List<InterbankTransfer> transfers = interbankTransferRepository.findAll();
        return ResponseEntity.ok(transfers);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<InterbankTransfer> getInterbankTransferById(@PathVariable Integer id) {
        Optional<InterbankTransfer> transfer = interbankTransferRepository.findById(id);
        return transfer.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<InterbankTransfer> createInterbankTransfer(@RequestBody InterbankTransfer transfer) {
        InterbankTransfer savedTransfer = interbankTransferRepository.save(transfer);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTransfer);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<InterbankTransfer> updateInterbankTransfer(@PathVariable Integer id, @RequestBody InterbankTransfer transferDetails) {
        Optional<InterbankTransfer> optionalTransfer = interbankTransferRepository.findById(id);
        if (optionalTransfer.isPresent()) {
            InterbankTransfer transfer = optionalTransfer.get();
            transfer.setFromAccount(transferDetails.getFromAccount());
            transfer.setToBank(transferDetails.getToBank());
            transfer.setToAccountNumber(transferDetails.getToAccountNumber());
            transfer.setAmount(transferDetails.getAmount());
            transfer.setDate(transferDetails.getDate());
            transfer.setStatus(transferDetails.getStatus());
            
            InterbankTransfer updatedTransfer = interbankTransferRepository.save(transfer);
            return ResponseEntity.ok(updatedTransfer);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInterbankTransfer(@PathVariable Integer id) {
        if (interbankTransferRepository.existsById(id)) {
            interbankTransferRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
