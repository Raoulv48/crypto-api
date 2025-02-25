package com.example.crypto_api.config;

import com.example.crypto_api.entity.Currency;
import com.example.crypto_api.repository.CurrencyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final CurrencyRepository currencyRepository;

    public DataLoader(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Preload data into the H2 database
        currencyRepository.save(new Currency.Builder()
                .ticker("BTC")
                .name("Bitcoin")
                .numberOfCoins(16770000L)
                .marketCap("$189,580,000,000")
                .build());

        currencyRepository.save(new Currency.Builder()
                .ticker("ETH")
                .name("Ethereum")
                .numberOfCoins(96710000L)
                .marketCap("$69,280,000,000")
                .build());

        currencyRepository.save(new Currency.Builder()
                .ticker("XRP")
                .name("Ripple")
                .numberOfCoins(38590000000L)
                .marketCap("$64,750,000,000")
                .build());

        currencyRepository.save(new Currency.Builder()
                .ticker("BCH")
                .name("BitcoinCash")
                .numberOfCoins(16670000L)
                .marketCap("$69,020,000,000")
                .build());

        System.out.println("DataLoader: Preloaded sample cryptocurrency data.");
    }
}