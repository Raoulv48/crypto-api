package com.example.crypto_api.controller;

import com.example.crypto_api.entity.Currency;
import com.example.crypto_api.repository.CurrencyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
@AutoConfigureMockMvc
class CurrencyControllerIntergrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CurrencyRepository currencyRepository;

    @BeforeEach
    void setup() {
        currencyRepository.deleteAll(); // Ensure a clean state before each test.
    }

    @Test
    void testGetAllCurrencies_EmptyList() throws Exception {
        mockMvc.perform(get("/currencies"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]")); // Should return an empty array.
    }

    @Test
    void testCreateAndGetCurrency() throws Exception {
        String jsonCurrency = """
                {
                    "ticker": "BTC",
                    "name": "Bitcoin",
                    "numberOfCoins": 21000000,
                    "marketCap": "$500B"
                }
                """;

        // Create a new Currency
        mockMvc.perform(post("/currencies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonCurrency))
                .andExpect(status().isCreated());

        // Verify it can be fetched
        mockMvc.perform(get("/currencies/BTC"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ticker").value("BTC"))
                .andExpect(jsonPath("$.name").value("Bitcoin"));
    }

    @Test
    void testCreateCurrency_InvalidInput() throws Exception {
        String invalidCurrencyJson = """
            {
                "ticker": "",
                "name": "Bitcoin",
                "numberOfCoins": -1,
                "marketCap": ""
            }
            """;

        mockMvc.perform(post("/currencies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidCurrencyJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.ticker").value("Ticker cannot be blank."))
                .andExpect(jsonPath("$.numberOfCoins").value("Number of coins cannot be negative."))
                .andExpect(jsonPath("$.marketCap").value("Market cap cannot be blank."));
    }

//    @Test
//    public void testPostCurrencyWithMissingFields_returnsBadRequest() throws Exception {
//        String invalidPayload = "{ \"name\": \"Bitcoin\" }"; // Missing ticker + market_cap
//
//        mockMvc.perform(post("/currencies")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(invalidPayload))
//                .andExpect(status().isBadRequest())
//                .andExpect(jsonPath("$.errors", hasSize(2)))
//                .andExpect(jsonPath("$.errors[0].field").value("ticker"))
//                .andExpect(jsonPath("$.errors[1].field").value("market_cap"));
//    }

//    @Test
//    public void testPostCurrencyWithInvalidValues_returnsBadRequest() throws Exception {
//        String invalidPayload = "{" +
//                " \"ticker\": \"BTC\", \"name\": \"Bitcoin\", " +
//                " \"number_of_coins\": -10, \"market_cap\": \"-5B\"" +
//                "}"; // Invalid negative values
//
//        mockMvc.perform(post("/currencies")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(invalidPayload))
//                .andExpect(status().isBadRequest())
//                .andExpect(jsonPath("$.errors", hasSize(2)))
//                .andExpect(jsonPath("$.errors[0].field").value("number_of_coins"))
//                .andExpect(jsonPath("$.errors[1].field").value("market_cap"));
//    }

    @Test
    public void testPostCurrencyWithEmptyValues_returnsBadRequest() throws Exception {
        String invalidPayload = "{" +
                "\"ticker\": \"\", \"name\": \"\", \"number_of_coins\": 0, \"market_cap\": \"\"" +
                "}";

        mockMvc.perform(post("/currencies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidPayload))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testUnsupportedEndpoint_shouldReturnMethodNotAllowed() throws Exception {
        mockMvc.perform(put("/currencies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"ticker\": \"BTC\", \"name\": \"Bitcoin\", \"number_of_coins\": 100, \"market_cap\": \"1B\" }"))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void testNonExistentEndpoint_shouldReturn404() throws Exception {
        mockMvc.perform(get("/non-existent-endpoint")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


}