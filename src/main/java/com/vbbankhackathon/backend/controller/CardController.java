package com.vbbankhackathon.backend.controller;

import com.vbbankhackathon.backend.entity.Card;
import com.vbbankhackathon.backend.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cards")
@CrossOrigin(origins = "*")
public class CardController {
    
    @Autowired
    private CardRepository cardRepository;
    
    @GetMapping
    public ResponseEntity<List<Card>> getAllCards() {
        List<Card> cards = cardRepository.findAll();
        return ResponseEntity.ok(cards);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Card> getCardById(@PathVariable Integer id) {
        Optional<Card> card = cardRepository.findById(id);
        return card.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Card> createCard(@RequestBody Card card) {
        Card savedCard = cardRepository.save(card);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCard);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Card> updateCard(@PathVariable Integer id, @RequestBody Card cardDetails) {
        Optional<Card> optionalCard = cardRepository.findById(id);
        if (optionalCard.isPresent()) {
            Card card = optionalCard.get();
            card.setAccountId(cardDetails.getAccountId());
            card.setCardNumber(cardDetails.getCardNumber());
            card.setExpirationDate(cardDetails.getExpirationDate());
            card.setCvv(cardDetails.getCvv());
            card.setCardType(cardDetails.getCardType());
            card.setStatus(cardDetails.getStatus());
            
            Card updatedCard = cardRepository.save(card);
            return ResponseEntity.ok(updatedCard);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCard(@PathVariable Integer id) {
        if (cardRepository.existsById(id)) {
            cardRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
