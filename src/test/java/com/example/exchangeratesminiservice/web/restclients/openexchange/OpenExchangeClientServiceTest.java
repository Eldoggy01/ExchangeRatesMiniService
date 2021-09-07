package com.example.exchangeratesminiservice.web.restclients.openexchange;

import com.example.exchangeratesminiservice.utils.DateTimeUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import java.text.SimpleDateFormat;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
@Import(OpenExchangeClientService.class)
class OpenExchangeClientServiceTest {

    @Value("${quote-currency}")
    private String quoteCurrency;

    @MockBean
    private OpenExchangeClient openExchangeClient;

    @Autowired
    private OpenExchangeClientService testSubject;

    @BeforeEach
    void setUp() {
    }

    @Test
    void isRateBecameSmaller() {
        String investigatedCurrency = "EUR";

        String baseCurrencyInOpenExchangeClient = "USD";

        String timeStampYesterday = "1";
        String usdToInvestigatedCurrencyYesterday = "0.80";
        String usdToQuoteCurrencyYesterday = "72.1";

        OpenExchangeData yesterdayMockedResult =
            new OpenExchangeData(baseCurrencyInOpenExchangeClient, timeStampYesterday,
                Map.of(investigatedCurrency, usdToInvestigatedCurrencyYesterday, quoteCurrency, usdToQuoteCurrencyYesterday));

        String timeStampLatest = "2";
        String usdToInvestigatedCurrencyLatest = "0.89";
        String usdToQuoteCurrencyLatest = "72.2";

        OpenExchangeData latestMockedResult =
            new OpenExchangeData(baseCurrencyInOpenExchangeClient, timeStampLatest,
                Map.of(investigatedCurrency, usdToInvestigatedCurrencyLatest, quoteCurrency, usdToQuoteCurrencyLatest));

        when(openExchangeClient.getLatest()).thenReturn(latestMockedResult);

        String yesterdayDate = DateTimeUtil.getDateInSpecifiedFormat(-1, new SimpleDateFormat("yyyy-MM-dd"));
        when(openExchangeClient
            .getHistorical(yesterdayDate)).thenReturn(yesterdayMockedResult);

        ArgumentCaptor<String> dateCaptor = ArgumentCaptor.forClass(String.class);

        //when
        boolean realResult = testSubject.isRateBecameSmaller(investigatedCurrency);

        //then
        verify(openExchangeClient).getLatest();
        verify(openExchangeClient).getHistorical(dateCaptor.capture());

        assertThat(dateCaptor.getValue()).isEqualTo(yesterdayDate);
        assertThat(realResult).isTrue();
    }
}