package com.capstone.moayo.service.dto;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;

public class RequestForm {
    @SerializedName("higher_layer")
    private List<String> higher_layer;
    @SerializedName("lower_layer")
    private List<String> lower_layer;
    @SerializedName("higher_layer_cache")
    private String[] higher_layer_cache;
    @SerializedName("lower_layer_cache")
    private String[] lower_layer_cache;

    public List<String> getHigher_layer() {
        return higher_layer;
    }

    public void setHigher_layer(List<String> higher_layer) {
        this.higher_layer = higher_layer;
    }

    public List<String> getLower_layer() {
        return lower_layer;
    }

    public void setLower_layer(List<String> lower_layer) {
        this.lower_layer = lower_layer;
    }

    public String[] getHigher_layer_cache() {
        return higher_layer_cache;
    }

    public void setHigher_layer_cache(String[] higher_layer_cache) {
        this.higher_layer_cache = higher_layer_cache;
    }

    public String[] getLower_layer_cache() {
        return lower_layer_cache;
    }

    public void setLower_layer_cache(String[] lower_layer_cache) {
        this.lower_layer_cache = lower_layer_cache;
    }

    @Override
    public String toString() {
        return "RequestForm{" +
                "higher_layer=" + higher_layer +
                ", lower_layer=" + lower_layer +
                ", higher_layer_cache=" + Arrays.toString(higher_layer_cache) +
                ", lower_layer_cache=" + Arrays.toString(lower_layer_cache) +
                '}';
    }
}
