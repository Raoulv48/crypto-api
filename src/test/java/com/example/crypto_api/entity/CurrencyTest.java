package com.example.crypto_api.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CurrencyTest {

    @Test
    void testCurrencyBuilder() {
        Currency currency = new Currency.Builder()
                .ticker("BTC")
                .name("Bitcoin")
                .numberOfCoins(21000000L)
                .marketCap("$500B")
                .build();

        assertNotNull(currency);
        assertEquals("BTC", currency.getTicker());
        assertEquals("Bitcoin", currency.getName());
        assertEquals(21000000L, currency.getNumberOfCoins());
        assertEquals("$500B", currency.getMarketCap());
    }

    @Test
    void testSetterMethods() {
        Currency currency = new Currency();
        currency.setTicker("ETH");
        currency.setName("Ethereum");
        currency.setNumberOfCoins(120000000L);
        currency.setMarketCap("$200B");

        assertEquals("ETH", currency.getTicker());
        assertEquals("Ethereum", currency.getName());
        assertEquals(120000000L, currency.getNumberOfCoins());
        assertEquals("$200B", currency.getMarketCap());
    }
}