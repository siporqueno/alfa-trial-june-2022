package com.niko.feign.clients;

import com.niko.dto.RateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "rates-client", url = "${properties.rates.url}")
public interface RatesClient {

    @GetMapping(value = "${properties.rates.historical_endpoint}/{date}")
    RateResponse getHistoricalRate(@PathVariable String date, @RequestParam(name = "app_id") String appId, @RequestParam String symbols);

    @GetMapping(value = "${properties.rates.latest_endpoint}")
    RateResponse getLatestRate(@RequestParam(name = "app_id") String appId, @RequestParam String symbols);

}
