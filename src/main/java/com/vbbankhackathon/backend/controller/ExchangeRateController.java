package com.vbbankhackathon.backend.controller;

import com.vbbankhackathon.backend.entity.ExchangeRate;
import com.vbbankhackathon.backend.repository.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/exchange-rates")
@CrossOrigin(originPatterns = "*")
public class ExchangeRateController {
    
    @Autowired
    private ExchangeRateRepository exchangeRateRepository;
    
    @GetMapping
    public ResponseEntity<List<ExchangeRate>> getAllExchangeRates() {
        List<ExchangeRate> rates = exchangeRateRepository.findAll();
        return ResponseEntity.ok(rates);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ExchangeRate> getExchangeRateById(@PathVariable Integer id) {
        Optional<ExchangeRate> rate = exchangeRateRepository.findById(id);
        return rate.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<ExchangeRate> createExchangeRate(@RequestBody ExchangeRate rate) {
        ExchangeRate savedRate = exchangeRateRepository.save(rate);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRate);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ExchangeRate> updateExchangeRate(@PathVariable Integer id, @RequestBody ExchangeRate rateDetails) {
        Optional<ExchangeRate> optionalRate = exchangeRateRepository.findById(id);
        if (optionalRate.isPresent()) {
            ExchangeRate rate = optionalRate.get();
            rate.setCurrencyFrom(rateDetails.getCurrencyFrom());
            rate.setCurrencyTo(rateDetails.getCurrencyTo());
            rate.setRate(rateDetails.getRate());
            rate.setDate(rateDetails.getDate());
            
            ExchangeRate updatedRate = exchangeRateRepository.save(rate);
            return ResponseEntity.ok(updatedRate);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExchangeRate(@PathVariable Integer id) {
        if (exchangeRateRepository.existsById(id)) {
            exchangeRateRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
