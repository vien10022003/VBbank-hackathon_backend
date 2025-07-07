package com.vbbankhackathon.backend.controller;

import com.vbbankhackathon.backend.entity.Portfolio;
import com.vbbankhackathon.backend.repository.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/portfolios")
@CrossOrigin(origins = "*")
public class PortfolioController {
    
    @Autowired
    private PortfolioRepository portfolioRepository;
    
    @GetMapping
    public ResponseEntity<List<Portfolio>> getAllPortfolios() {
        List<Portfolio> portfolios = portfolioRepository.findAll();
        return ResponseEntity.ok(portfolios);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Portfolio> getPortfolioById(@PathVariable Integer id) {
        Optional<Portfolio> portfolio = portfolioRepository.findById(id);
        return portfolio.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Portfolio> createPortfolio(@RequestBody Portfolio portfolio) {
        Portfolio savedPortfolio = portfolioRepository.save(portfolio);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPortfolio);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Portfolio> updatePortfolio(@PathVariable Integer id, @RequestBody Portfolio portfolioDetails) {
        Optional<Portfolio> optionalPortfolio = portfolioRepository.findById(id);
        if (optionalPortfolio.isPresent()) {
            Portfolio portfolio = optionalPortfolio.get();
            portfolio.setCustomerId(portfolioDetails.getCustomerId());
            portfolio.setStockId(portfolioDetails.getStockId());
            portfolio.setQuantity(portfolioDetails.getQuantity());
            portfolio.setPurchasePrice(portfolioDetails.getPurchasePrice());
            portfolio.setPurchaseDate(portfolioDetails.getPurchaseDate());
            
            Portfolio updatedPortfolio = portfolioRepository.save(portfolio);
            return ResponseEntity.ok(updatedPortfolio);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePortfolio(@PathVariable Integer id) {
        if (portfolioRepository.existsById(id)) {
            portfolioRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
