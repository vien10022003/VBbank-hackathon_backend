package com.vbbankhackathon.backend.controller;

import com.vbbankhackathon.backend.entity.Transaction;
import com.vbbankhackathon.backend.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin(originPatterns = "*")
public class TransactionController {
    
    @Autowired
    private TransactionRepository transactionRepository;
    
    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        return ResponseEntity.ok(transactions);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Integer id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        return transaction.map(ResponseEntity::ok)
                         .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        Transaction savedTransaction = transactionRepository.save(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTransaction);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable Integer id, @RequestBody Transaction transactionDetails) {
        Optional<Transaction> optionalTransaction = transactionRepository.findById(id);
        if (optionalTransaction.isPresent()) {
            Transaction transaction = optionalTransaction.get();
            transaction.setAccountId(transactionDetails.getAccountId());
            transaction.setTransactionType(transactionDetails.getTransactionType());
            transaction.setAmount(transactionDetails.getAmount());
            transaction.setTransactionDate(transactionDetails.getTransactionDate());
            transaction.setDescription(transactionDetails.getDescription());
            transaction.setTransactionMethod(transactionDetails.getTransactionMethod());
            transaction.setMerchantId(transactionDetails.getMerchantId());
            transaction.setCurrency(transactionDetails.getCurrency());
            transaction.setExchangeRate(transactionDetails.getExchangeRate());
            
            Transaction updatedTransaction = transactionRepository.save(transaction);
            return ResponseEntity.ok(updatedTransaction);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Integer id) {
        if (transactionRepository.existsById(id)) {
            transactionRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
