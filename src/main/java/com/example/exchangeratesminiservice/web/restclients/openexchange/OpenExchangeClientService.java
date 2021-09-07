package com.example.exchangeratesminiservice.web.restclients.openexchange;

import com.example.exchangeratesminiservice.utils.DateTimeUtil;
import com.example.exchangeratesminiservice.web.api.CurrencyCodeNotValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;

/**
 * Класс для работы с внешним сервисом получения курсов валют openexchangerates.org
 *
 * Использованы костыли для определения курсов между валютами на основании
 * курса доллара к этим валютам, т.к на бесплатном аккаунте в openexchangerates.org
 * можно получить курсы валют только относительно доллара.
 */

@Service
public class OpenExchangeClientService {
    private OpenExchangeClient openExchangeClient;

    @Value("${quote-currency}")
    private String quoteCurrency;

    @Autowired
    public OpenExchangeClientService(OpenExchangeClient openExchangeClient) {
        this.openExchangeClient = openExchangeClient;
    }

    /**
     * Проверяет, уменьшился ли по сравнению со вчерашним днем
     * курс передаваемой в метод валюты по отношению к валюте, находящейся в
     * application.yml с ключем 'quote-currency'.
     *
     * @param investigatedCurrency код валюты, изменение курса которой нужно проанализировать
     *
     *                             Returns   true, если он уменьшился,   false - если увеличился.
     */
    public boolean isRateBecameSmaller(String investigatedCurrency) {
        OpenExchangeData openExchangeDataLatest = openExchangeClient.getLatest();
        checkCurrencyCodeExists(openExchangeDataLatest, investigatedCurrency);
        BigDecimal investigatedToQuoteRateLatest = getRateBetweenCurrenciesByUsdRates(openExchangeDataLatest,
            investigatedCurrency, quoteCurrency);

        String yesterdayDate = DateTimeUtil.getDateInSpecifiedFormat(-1, new SimpleDateFormat("yyyy-MM-dd"));
        OpenExchangeData openExchangeDataYesterday = openExchangeClient.getHistorical(yesterdayDate);

        BigDecimal investigatedToQuoteRateYesterday = getRateBetweenCurrenciesByUsdRates(openExchangeDataYesterday,
            investigatedCurrency, quoteCurrency);

        return investigatedToQuoteRateLatest.compareTo(investigatedToQuoteRateYesterday) < 0;
    }

    /**
     * Костыль, сделанный из-за того, что на бесплатном аккаунте в openexchangerates.org
     * можно получить курсы валют только относительно доллара.
     * Считает курс между двумя валютами на основании курсов доллара
     * по отношению к этим валютам
     *
     * @param openExchangeDataLatest объект с курсом доллара по отношению к остальным валютам
     * @param baseCurrency           код валюты, курс которой хотим получить относительно второй валюты
     * @param quoteCurrency          код валюты, по отношению к которой будем считать курс первой валюты
     *
     *                               Returns  {@code BigDecimal}  курс валюты baseCurrency относительно валюты quoteCurrency
     */
    private BigDecimal getRateBetweenCurrenciesByUsdRates(OpenExchangeData openExchangeDataLatest, String baseCurrency, String quoteCurrency) {
        String usdToBaseCurrency = openExchangeDataLatest.getRates().get(baseCurrency);
        String usdToQuoteCurrency = openExchangeDataLatest.getRates().get(quoteCurrency);
        return getRateBetweenCurrenciesByUsdRates(usdToBaseCurrency, usdToQuoteCurrency);
    }

    /**
     * Костыль, сделанный из-за того, что на бесплатном аккаунте в openexchangerates.org
     * можно получить курсы валют только относительно доллара.
     * Считает курс между двумя валютами на основании курсов доллара
     * по отношению к этим валютам
     *
     * @param usdToCurrency1 курс доллара по отношению к первой валюте
     * @param usdToCurrency2 курс доллара по отношению ко второй валюте
     *
     *                       Returns  {@code BigDecimal}  курс первой валюты относительно второй
     */
    private BigDecimal getRateBetweenCurrenciesByUsdRates(String usdToCurrency1, String usdToCurrency2) {
        var usdToCurrency1BigDecimal = new BigDecimal(usdToCurrency1);
        var usdToCurrency2BigDecimal = new BigDecimal(usdToCurrency2);
        return usdToCurrency2BigDecimal.divide(usdToCurrency1BigDecimal, 20, RoundingMode.HALF_UP);
    }

    private void checkCurrencyCodeExists(OpenExchangeData openExchangeDataLatest, String curCode) {
        if (!openExchangeDataLatest.getRates().containsKey(curCode)) {
            throw new CurrencyCodeNotValidException("Currency code '".concat(curCode).concat("' does not exists!"));
        }
    }
}
