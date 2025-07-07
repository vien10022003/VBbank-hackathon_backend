package com.vbbankhackathon.backend.controller;

import com.vbbankhackathon.backend.entity.Stock;
import com.vbbankhackathon.backend.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stocks")
@CrossOrigin(originPatterns = "*")
public class StockController {
    
    @Autowired
    private StockRepository stockRepository;
    
    @GetMapping
    public ResponseEntity<List<Stock>> getAllStocks() {
        List<Stock> stocks = stockRepository.findAll();
        return ResponseEntity.ok(stocks);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Stock> getStockById(@PathVariable Integer id) {
        Optional<Stock> stock = stockRepository.findById(id);
        return stock.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Stock> createStock(@RequestBody Stock stock) {
        Stock savedStock = stockRepository.save(stock);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStock);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Stock> updateStock(@PathVariable Integer id, @RequestBody Stock stockDetails) {
        Optional<Stock> optionalStock = stockRepository.findById(id);
        if (optionalStock.isPresent()) {
            Stock stock = optionalStock.get();
            stock.setSymbol(stockDetails.getSymbol());
            stock.setCompanyName(stockDetails.getCompanyName());
            stock.setCurrentPrice(stockDetails.getCurrentPrice());
            
            Stock updatedStock = stockRepository.save(stock);
            return ResponseEntity.ok(updatedStock);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable Integer id) {
        if (stockRepository.existsById(id)) {
            stockRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
