package com.niko.controllers;

import com.niko.services.GifsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/currency-change")
public class CurrencyChangeController {

    private final GifsService gifsService;

    public CurrencyChangeController(GifsService gifsService) {
        this.gifsService = gifsService;
    }

    @GetMapping("/{currencyCode}")
    void renderCurrencyChangeGif(@PathVariable String currencyCode, HttpServletResponse response) throws IOException {
        response.sendRedirect(gifsService.renderGifUrl(currencyCode));
    }

}
