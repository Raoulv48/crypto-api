package com.example.crypto_api.repository;

import com.example.crypto_api.entity.Currency;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CurrencyRepositoryTest {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Test
    void testSaveAndFindCurrency() {
        Currency currency = new Currency.Builder()
                .ticker("SOL")
                .name("Solana")
                .numberOfCoins(500000000L)
                .marketCap("$12B")
                .build();

        // Save the entity
        currencyRepository.save(currency);

        // Fetch it back
        Optional<Currency> fetchedCurrency = currencyRepository.findById("SOL");

        assertTrue(fetchedCurrency.isPresent());
        assertEquals("Solana", fetchedCurrency.get().getName());
    }

    @Test
    void testDeleteCurrency() {
        Currency currency = new Currency.Builder()
                .ticker("DOGE")
                .name("Dogecoin")
                .numberOfCoins(100000000000L)
                .marketCap("$10B")
                .build();

        currencyRepository.save(currency);

        // Delete the entity
        currencyRepository.deleteById("DOGE");

        // Verify it no longer exists
        Optional<Currency> fetchedCurrency = currencyRepository.findById("DOGE");

        assertFalse(fetchedCurrency.isPresent());
    }
}