package com.example.crypto_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;

//import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "currencies")
public class Currency {
    @Id
//    @JsonProperty("ticker")
    @NotBlank(message = "Ticker cannot be blank.")
    private String ticker;

//    @JsonProperty("name")
    @NotBlank(message = "Name cannot be blank.")
    private String name;

//    @JsonProperty("numberofcoins")
    @Min(value = 0, message = "Number of coins cannot be negative.")
    private long numberOfCoins;

//    @JsonProperty("marketcap")
    @NotBlank(message = "Market cap cannot be blank.")
    private String marketCap;

    // No-args constructor (required by JPA)
    public Currency() {}

    // Private constructor for the Builder pattern
    private Currency(Builder builder) {
        this.ticker = builder.ticker;
        this.name = builder.name;
        this.numberOfCoins = builder.numberOfCoins;
        this.marketCap = builder.marketCap;
    }

    // === Getters and Setters ===
    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getNumberOfCoins() {
        return numberOfCoins;
    }

    public void setNumberOfCoins(long numberOfCoins) {
        this.numberOfCoins = numberOfCoins;
    }

    public String getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(String marketCap) {
        this.marketCap = marketCap;
    }

    // === Builder Pattern ===
    public static class Builder {
        private String ticker;
        private String name;
        private long numberOfCoins;
        private String marketCap;

        public Builder ticker(String ticker) {
            this.ticker = ticker;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder numberOfCoins(long numberOfCoins) {
            this.numberOfCoins = numberOfCoins;
            return this;
        }

        public Builder marketCap(String marketCap) {
            this.marketCap = marketCap;
            return this;
        }

        public Currency build() {
            return new Currency(this);
        }
    }
}