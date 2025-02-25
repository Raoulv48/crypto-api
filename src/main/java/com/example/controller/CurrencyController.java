package com.example.crypto_api.controller;

import com.example.crypto_api.entity.Currency;
import com.example.crypto_api.repository.CurrencyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {

    private final CurrencyRepository currencyRepository;

    public CurrencyController(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    // === Create a new Currency ===
    @PostMapping
    public ResponseEntity<Currency> createCurrency(@Valid @RequestBody Currency currency) {
        Currency savedCurrency = currencyRepository.save(currency);
        return new ResponseEntity<>(savedCurrency, HttpStatus.CREATED);
    }

    // === Get all Currencies ===
    @GetMapping
    public List<Currency> getAllCurrencies() {
        return currencyRepository.findAll();
    }

    // === Get a specific Currency by Ticker ===
    @GetMapping("/{ticker}")
    public ResponseEntity<Currency> getCurrencyByTicker(@PathVariable String ticker) {
        Optional<Currency> currency = currencyRepository.findById(ticker);
        return currency.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // === Update a Currency ===
    @PutMapping("/{ticker}")
    public ResponseEntity<Currency> updateCurrency(
            @PathVariable String ticker,
            @Valid @RequestBody Currency updatedCurrency) {

        // Check if the currency exists
        if (!currencyRepository.existsById(ticker)) {
            return ResponseEntity.notFound().build();
        }

        // Update and save the currency
        updatedCurrency.setTicker(ticker); // Ensure that the ticker remains the same
        Currency savedCurrency = currencyRepository.save(updatedCurrency);
        return ResponseEntity.ok(savedCurrency);
    }

    // === Delete a Currency ===
    @DeleteMapping("/{ticker}")
    public ResponseEntity<Void> deleteCurrency(@PathVariable String ticker) {
        // Check existence before deleting
        if (!currencyRepository.existsById(ticker)) {
            return ResponseEntity.notFound().build();
        }

        currencyRepository.deleteById(ticker);
        return ResponseEntity.noContent().build();
    }
}