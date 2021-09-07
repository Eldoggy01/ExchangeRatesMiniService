package com.example.exchangeratesminiservice.web.api;

import com.example.exchangeratesminiservice.web.restclients.giphy.GiphyClientService;
import com.example.exchangeratesminiservice.web.restclients.openexchange.OpenExchangeClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Map;


@RestController
@RequestMapping("/api")
@Validated
public class ExchangeRatesController {

    private OpenExchangeClientService openExchangeClientService;
    private GiphyClientService giphyClientService;

    @Autowired
    public ExchangeRatesController(OpenExchangeClientService openExchangeClientService, GiphyClientService giphyClientService) {
        this.openExchangeClientService = openExchangeClientService;
        this.giphyClientService = giphyClientService;
    }

    @GetMapping("/current-day-success/{currencyCode}")
    public Map<String,String> getCurrentDaySuccess(@PathVariable @NotBlank @NotEmpty(message = "currencyCode не должно быть пустым") @NotNull String currencyCode) {
        if (openExchangeClientService.isRateBecameSmaller(currencyCode)){
            return giphyClientService.getGifRandomBroke();
        } else {
            return giphyClientService.getGifRandomRich();
        }
    }


}
