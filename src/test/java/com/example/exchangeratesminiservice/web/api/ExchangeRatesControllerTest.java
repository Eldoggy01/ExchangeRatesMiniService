package com.example.exchangeratesminiservice.web.api;

import com.example.exchangeratesminiservice.web.restclients.giphy.GiphyClientService;
import com.example.exchangeratesminiservice.web.restclients.openexchange.OpenExchangeClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
class ExchangeRatesControllerTest {
    private String urlKey = "url";
    private String richUrl = "richUrl";
    private String brokeUrl = "brokeUrl";

    @MockBean
    private OpenExchangeClientService openExchangeClientService;

    @MockBean
    private GiphyClientService giphyClientService;

    @Autowired
    private ExchangeRatesController testSubject;

    @BeforeEach
    void setUp() {
        Mockito.when(giphyClientService.getGifRandomRich()).thenReturn(Map.of(urlKey, richUrl));
        Mockito.when(giphyClientService.getGifRandomBroke()).thenReturn(Map.of(urlKey, brokeUrl));
    }

    @Test
    void getCurrentDaySuccessBroken() {
        String investigatedCurrency = "EUR";

        Mockito.when(openExchangeClientService.isRateBecameSmaller(investigatedCurrency)).thenReturn(true);

        ArgumentCaptor<String> currencyCaptor = ArgumentCaptor.forClass(String.class);

        //when
        Map<String, String> realResult = testSubject.getCurrentDaySuccess(investigatedCurrency);

        //then
        Mockito.verify(openExchangeClientService).isRateBecameSmaller(currencyCaptor.capture());
        assertThat(currencyCaptor.getValue()).isEqualTo(investigatedCurrency);

        Mockito.verify(giphyClientService).getGifRandomBroke();

        assertThat(realResult).hasSize(1).containsEntry(urlKey, brokeUrl);
    }

    @Test
    void getCurrentDaySuccessRich() {
        String investigatedCurrency = "EUR";

        Mockito.when(openExchangeClientService.isRateBecameSmaller(investigatedCurrency)).thenReturn(false);

        ArgumentCaptor<String> currencyCaptor = ArgumentCaptor.forClass(String.class);

        //when
        Map<String, String> realResult = testSubject.getCurrentDaySuccess(investigatedCurrency);

        //then
        Mockito.verify(openExchangeClientService).isRateBecameSmaller(currencyCaptor.capture());
        assertThat(currencyCaptor.getValue()).isEqualTo(investigatedCurrency);

        Mockito.verify(giphyClientService).getGifRandomRich();

        assertThat(realResult).hasSize(1).containsEntry(urlKey, richUrl);
    }
}
