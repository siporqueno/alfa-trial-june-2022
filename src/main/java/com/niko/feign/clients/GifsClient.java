package com.niko.feign.clients;

import com.niko.dto.GifResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "gifs-client", url = "${properties.gifs.url}")
public interface GifsClient {

    @GetMapping
    GifResponse getRandomGif(@RequestParam(name="api_key") String apiKey, @RequestParam String tag);
}
