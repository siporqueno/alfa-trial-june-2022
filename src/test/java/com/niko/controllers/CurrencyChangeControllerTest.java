package com.niko.controllers;

import com.niko.services.GifsService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class CurrencyChangeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GifsService gifsService;

    @Test
    public void renderCurrencyChangeGifTest() throws Exception {

        Mockito.doReturn("RichUrl")
                .when(gifsService)
                .renderGifUrl("EUR");

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/currency-change/EUR"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("RichUrl"));
    }
}
