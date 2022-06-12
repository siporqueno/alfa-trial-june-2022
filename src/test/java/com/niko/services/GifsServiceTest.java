package com.niko.services;

import com.niko.dto.Data;
import com.niko.dto.GifResponse;
import com.niko.dto.Image;
import com.niko.feign.clients.GifsClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Map;

@SpringBootTest
public class GifsServiceTest {

    @Value("${properties.gifs.api_key}")
    private String apiKey;

    @Autowired
    private GifsService gifsService;

    @MockBean
    private RatesService ratesService;

    @MockBean
    private GifsClient gifsClient;

    @Test
    public void renderGifUrlTestWhenRateIsUp() {

        GifResponse gifResponseRich = new GifResponse(new Data(Map.of("original", new Image("RichUrl"))));

        Mockito.doReturn(true)
                .when(ratesService)
                .isRateUp("EUR");

        Mockito.doReturn(gifResponseRich)
                .when(gifsClient)
                .getRandomGif(apiKey, "rich");

        Assertions.assertNotNull(gifsService.renderGifUrl("EUR"));
        Assertions.assertEquals("RichUrl", gifsService.renderGifUrl("EUR"));
    }

    @Test
    public void renderGifUrlTestWhenRateIsDown() {

        GifResponse gifResponseRich = new GifResponse(new Data(Map.of("original", new Image("BrokeUrl"))));

        Mockito.doReturn(false)
                .when(ratesService)
                .isRateUp("EUR");

        Mockito.doReturn(gifResponseRich)
                .when(gifsClient)
                .getRandomGif(apiKey, "broke");

        Assertions.assertNotNull(gifsService.renderGifUrl("EUR"));
        Assertions.assertEquals("BrokeUrl", gifsService.renderGifUrl("EUR"));
    }
}
