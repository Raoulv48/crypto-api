package com.example.crypto_api.repository;

import com.example.crypto_api.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, String> {
    // Spring Data JPA automatically creates the implementation for basic CRUD operations.
}