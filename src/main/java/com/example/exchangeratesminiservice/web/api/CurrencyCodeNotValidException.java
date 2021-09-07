package com.example.exchangeratesminiservice.web.api;

/**
 * todo Document type SeatNotAvailableException
 */
public class CurrencyCodeNotValidException extends RuntimeException {
    public CurrencyCodeNotValidException(String reason) {
        super(reason);
    }
}
