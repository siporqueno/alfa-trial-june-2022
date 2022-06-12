package com.niko.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {
    Map<String, Image> images;

    public Data() {
    }

    public Data(Map<String, Image> images) {
        this.images = images;
    }

    public Map<String, Image> getImages() {
        return images;
    }

    public void setImages(Map<String, Image> images) {
        this.images = images;
    }
}
