package com.niko.services;

import com.niko.feign.clients.RatesClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RatesService {

    @Value("${properties.rates.app_id}")
    String appId;

    private RatesClient ratesClient;

    @Autowired
    public void setRatesClient(RatesClient ratesClient) {
        this.ratesClient = ratesClient;
    }

    public boolean isRateUp(String currencyCode) {

        LocalDate yesterday = LocalDate.now().minusDays(1);

        Float rateOfYesterday = ratesClient.getHistoricalRate(String.format("%s.json", yesterday), appId, currencyCode).getRates().get(currencyCode);
        Float rateOfToday = ratesClient.getLatestRate(appId, currencyCode).getRates().get(currencyCode);

        return 1 / rateOfToday >= 1 / rateOfYesterday;
    }
}
