package com.example.exchangeratesminiservice.web.restclients.giphy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
class GiphyClientServiceTest {

    private String urlKey = "url";
    private final String RICH_TAG = "rich";
    private final String BROKE_TAG = "broke";
    private GiphyGif giphyGifReturnedByMock;
    private ArgumentCaptor<String> apiKeyCaptor;
    private ArgumentCaptor<String> tagCaptor;

    @Value("${giphy.api-key}")
    private String apiKey;

    @MockBean
    private GiphyClient giphyClient;

    @Autowired
    GiphyClientService testSubject;

    @BeforeEach
    void setUp() {
        giphyGifReturnedByMock = new GiphyGif(new GiphyGif.Data("gif", "someId", "someUrl"));
        apiKeyCaptor = ArgumentCaptor.forClass(String.class);
        tagCaptor = ArgumentCaptor.forClass(String.class);
    }

    @Test
    void getGifRandomRich() {
        Mockito.when(giphyClient.getRandomGif(apiKey, RICH_TAG)).thenReturn(giphyGifReturnedByMock);

        //when
        Map<String, String> realMap = testSubject.getGifRandomRich();

        //then
        Mockito.verify(giphyClient).getRandomGif(apiKeyCaptor.capture(), tagCaptor.capture());
        assertThat(apiKeyCaptor.getValue()).isEqualTo(apiKey);
        assertThat(tagCaptor.getValue()).isEqualTo(RICH_TAG);
        assertThat(realMap).hasSize(1).containsEntry(urlKey, giphyGifReturnedByMock.getData().getUrl());
    }

    @Test
    void getGifRandomBroke() {
        Mockito.when(giphyClient.getRandomGif(apiKey, BROKE_TAG)).thenReturn(giphyGifReturnedByMock);

        //when
        Map<String, String> realMap = testSubject.getGifRandomBroke();

        //then
        Mockito.verify(giphyClient).getRandomGif(apiKeyCaptor.capture(), tagCaptor.capture());
        assertThat(apiKeyCaptor.getValue()).isEqualTo(apiKey);
        assertThat(tagCaptor.getValue()).isEqualTo(BROKE_TAG);

        assertThat(realMap).hasSize(1).containsEntry(urlKey, giphyGifReturnedByMock.getData().getUrl());
    }
}