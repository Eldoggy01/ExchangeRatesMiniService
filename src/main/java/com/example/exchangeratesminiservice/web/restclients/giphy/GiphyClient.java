package com.example.exchangeratesminiservice.web.restclients.giphy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "${giphy.value}", url = "${giphy.baseUrl}")
public interface GiphyClient {

    @GetMapping(value = "/v1/gifs/random")
    GiphyGif getRandomGif (@RequestParam("api_key") String apiKey, @RequestParam String tag);

}
