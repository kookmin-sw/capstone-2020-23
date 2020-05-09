package com.capstone.moayo.service.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RequestForm {
    @SerializedName("second_layer")
    private List<String> second_layer;
    @SerializedName("third_layer")
    private List<String> third_layer;
    @SerializedName("second_layer_cache")
    private String[] second_layer_cache;
    @SerializedName("third_layer_cache")
    private String[] third_layer_cache;

    public List<String> getSecond_layer() {
        return second_layer;
    }

    public void setSecond_layer(List<String> second_layer) {
        this.second_layer = second_layer;
    }

    public List<String> getThird_layer() {
        return third_layer;
    }

    public void setThird_layer(List<String> third_layer) {
        this.third_layer = third_layer;
    }


    public String[] getSecond_layer_cache() {
        return second_layer_cache;
    }

    public void setSecond_layer_cache(String[] second_layer_cache) {
        this.second_layer_cache = second_layer_cache;
    }

    public String[] getThird_layer_cache() {
        return third_layer_cache;
    }

    public void setThird_layer_cache(String[] third_layer_cache) {
        this.third_layer_cache = third_layer_cache;
    }
}
