package com.example.crypto_api.entity;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CurrencyValidationTest {

    private Validator validator;

    @BeforeEach
    void setup() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void whenTickerIsBlank_thenValidationFails() {
        Currency currency = new Currency();
        currency.setTicker(" "); // Invalid ticker
        currency.setName("Bitcoin");
        currency.setNumberOfCoins(21000000L);
        currency.setMarketCap("$500B");

        Set<jakarta.validation.ConstraintViolation<Currency>> violations = validator.validate(currency);

        assertEquals(1, violations.size());
        assertEquals("Ticker cannot be blank.", violations.iterator().next().getMessage());
    }
}