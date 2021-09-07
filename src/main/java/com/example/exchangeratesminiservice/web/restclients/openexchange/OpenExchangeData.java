package com.example.exchangeratesminiservice.web.restclients.openexchange;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Map;

@Data
@AllArgsConstructor
public class OpenExchangeData {
    private String base;
    private String timestamp;

    private Map<String, String> rates;
}
