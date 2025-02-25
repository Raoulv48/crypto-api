package com.example.crypto_api.service;

import com.example.crypto_api.entity.Currency;

import java.util.List;
import java.util.Optional;

public interface CurrencyService {
    Currency createCurrency(Currency currency);
    List<Currency> getAllCurrencies();
    Optional<Currency> getCurrencyByTicker(String ticker);
    Currency updateCurrency(String ticker, Currency updatedCurrency);
    void deleteCurrency(String ticker);
}