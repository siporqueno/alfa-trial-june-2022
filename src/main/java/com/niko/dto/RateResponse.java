package com.niko.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RateResponse {

    Map<String, Float> rates;

    public RateResponse() {
    }

    public RateResponse(Map<String, Float> rates) {
        this.rates = rates;
    }

    public Map<String, Float> getRates() {
        return rates;
    }

    public void setRates(Map<String, Float> rates) {
        this.rates = rates;
    }
}
