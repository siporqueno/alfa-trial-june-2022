package com.niko.services;

import com.niko.feign.clients.GifsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GifsService {

    @Value("${properties.gifs.api_key}")
    private String apiKey;

    private GifsClient gifsClient;

    private final RatesService ratesService;

    public GifsService(RatesService ratesService) {
        this.ratesService = ratesService;
    }

    @Autowired
    public void setGifsClient(GifsClient gifsClient) {
        this.gifsClient = gifsClient;
    }

    public String renderGifUrl(String currencyCode) {
        if (ratesService.isRateUp(currencyCode)) {
            return gifsClient.getRandomGif(apiKey, "rich").getData().getImages().get("original").getUrl();
        } else {
            return gifsClient.getRandomGif(apiKey, "broke").getData().getImages().get("original").getUrl();
        }
    }
}
