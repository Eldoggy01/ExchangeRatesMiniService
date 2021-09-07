package com.example.exchangeratesminiservice.web.restclients.openexchange;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@FeignClient(value = "${open-exchange.value}", url = "${open-exchange.baseUrl}", configuration = OpenExchangeConfiguration.class)
public interface OpenExchangeClient {

    @GetMapping(value = "/api/latest.json")
    OpenExchangeData getLatest ();

    @GetMapping(value = "/api/historical/{date}.json")
    OpenExchangeData getHistorical(@PathVariable String date);

}
