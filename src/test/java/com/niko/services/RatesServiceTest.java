package com.niko.services;

import com.niko.dto.RateResponse;
import com.niko.feign.clients.RatesClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class RatesServiceTest {

    @Value("${properties.rates.app_id}")
    String appId;

    @Autowired
    private RatesService ratesService;

    @MockBean
    private RatesClient ratesClient;

    @Test
    public void isRateUpTestWhenRateIsUp() {

        Map<String, Float> ratesHistorical = new HashMap<>();
        ratesHistorical.put("EUR", (float) 0.9);
        RateResponse rateResponseHistorical = new RateResponse(ratesHistorical);
        String yesterdayDate = String.format("%s.json", LocalDate.now().minusDays(1));

        Mockito.doReturn(rateResponseHistorical)
                .when(ratesClient)
                .getHistoricalRate(yesterdayDate, appId, "EUR");

        Map<String, Float> ratesLatest = new HashMap<>();
        ratesLatest.put("EUR", (float) 0.89);
        RateResponse rateResponseLatest = new RateResponse(ratesLatest);

        Mockito.doReturn(rateResponseLatest)
                .when(ratesClient)
                .getLatestRate(appId, "EUR");

        Assertions.assertTrue(ratesService.isRateUp("EUR"));

    }

    @Test
    public void isRateUpTestWhenRateIsUnchanged() {

        Map<String, Float> ratesHistorical = new HashMap<>();
        ratesHistorical.put("EUR", (float) 0.9);
        RateResponse rateResponseHistorical = new RateResponse(ratesHistorical);
        String yesterdayDate = String.format("%s.json", LocalDate.now().minusDays(1));

        Mockito.doReturn(rateResponseHistorical)
                .when(ratesClient)
                .getHistoricalRate(yesterdayDate, appId, "EUR");

        Map<String, Float> ratesLatest = new HashMap<>();
        ratesLatest.put("EUR", (float) 0.9);
        RateResponse rateResponseLatest = new RateResponse(ratesLatest);

        Mockito.doReturn(rateResponseLatest)
                .when(ratesClient)
                .getLatestRate(appId, "EUR");

        Assertions.assertTrue(ratesService.isRateUp("EUR"));

    }

    @Test
    public void isRateUpTestWhenRateIsDown() {

        Map<String, Float> ratesHistorical = new HashMap<>();
        ratesHistorical.put("EUR", (float) 0.9);
        RateResponse rateResponseHistorical = new RateResponse(ratesHistorical);
        String yesterdayDate = String.format("%s.json", LocalDate.now().minusDays(1));

        Mockito.doReturn(rateResponseHistorical)
                .when(ratesClient)
                .getHistoricalRate(yesterdayDate, appId, "EUR");

        Map<String, Float> ratesLatest = new HashMap<>();
        ratesLatest.put("EUR", (float) 0.91);
        RateResponse rateResponseLatest = new RateResponse(ratesLatest);

        Mockito.doReturn(rateResponseLatest)
                .when(ratesClient)
                .getLatestRate(appId, "EUR");

        Assertions.assertFalse(ratesService.isRateUp("EUR"));

    }
}
