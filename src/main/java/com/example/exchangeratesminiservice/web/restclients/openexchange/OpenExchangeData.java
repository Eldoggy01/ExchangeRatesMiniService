package com.example.exchangeratesminiservice.web.restclients.openexchange;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class OpenExchangeData {
    private String base;
    private String timestamp;

    private Map<String, String> rates;

//    public void saveToJson() {
//        List<String> ss = rates.keySet().stream().collect(Collectors.toList());
//        ObjectMapper mapper = new ObjectMapper();
//        Map<String, String> testMap = new HashMap<String, String>();
//
//        try {
//            mapper.writeValue(new File("/Users/eldar/IdeaProjects/ExchangeRatesMiniService/currencies.json"), testMap);
//        } catch (Exception s) {
//        }
//    }
}
