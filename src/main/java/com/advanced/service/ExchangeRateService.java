package com.advanced.service;

import com.advanced.ExchangeRateResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ExchangeRateService {
    private static final String API_URL = "https://open.er-api.com/v6/latest/USD";

    private final OkHttpClient client;
    private final ObjectMapper objectMapper;

    public ExchangeRateService() {
        this.client = new OkHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    /**
     * @param currency
     * @return
     * @throws IOException
     */
    public double getExchangeRate(String currency) throws IOException {
        Request request = new Request.Builder()
                .url(API_URL)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                ExchangeRateResponse exchangeRateResponse = objectMapper.readValue(response.body().string(), ExchangeRateResponse.class);
                Double rate = (Double) exchangeRateResponse.getRates(currency);
                if (rate != null) {
                    return rate;
                } else {
                    throw new IllegalArgumentException("Invalid currency code: " + currency);
                }
            } else {
                throw new IOException("API request failed with code: " + response.code());
            }
        }
    }
}