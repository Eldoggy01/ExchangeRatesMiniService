package com.example.exchangeratesminiservice.web.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class ExchangeRatesControllerIntTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getCurrentDaySuccess() throws Exception {
        String investigatedCurrency = "EUR";

        MvcResult result = this.mockMvc
            .perform(get("/api/current-day-success/{currencyCode}", investigatedCurrency))
            .andDo(print()).andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        HashMap<String,Object> map = objectMapper.readValue(responseBody, HashMap.class);
        assertThat(map).hasSize(1).containsKey("url");

    }
}