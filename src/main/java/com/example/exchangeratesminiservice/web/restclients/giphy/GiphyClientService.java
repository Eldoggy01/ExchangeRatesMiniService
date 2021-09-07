package com.example.exchangeratesminiservice.web.restclients.giphy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Класс для работы с внешним сервисом получения гифок  giphy.com
 */

@Service
public class GiphyClientService {

    private final String RICH_TAG = "rich";
    private final String BROKE_TAG = "broke";

    @Value("${giphy.api-key}")
    private String apiKey;

    private GiphyClient giphyClient;

    @Autowired
    public GiphyClientService(GiphyClient giphyClient) {
        this.giphyClient = giphyClient;
    }

    public Map<String,String> getGifRandomRich() {
       return getGifRandom(RICH_TAG);
    }

    public Map<String,String> getGifRandomBroke() {
        return getGifRandom(BROKE_TAG);
    }

    private Map<String,String> getGifRandom(String tag) {
        GiphyGif giphyGif = giphyClient.getRandomGif(apiKey, tag);
        return Map.of("url", giphyGif.getData().getUrl());
    }
}
