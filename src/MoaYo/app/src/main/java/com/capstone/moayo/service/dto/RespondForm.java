package com.capstone.moayo.service.dto;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;

public class RespondForm {
    @SerializedName("second_layer")
    private List<InstantPost> second_layer;
    @SerializedName("third_layer")
    private List<InstantPost> thrid_layer;
    @SerializedName("second_layer_cache")
    private String[] second_layer_cache;
    @SerializedName("third_layer_cache")
    private String[] third_layer_cache;

    public RespondForm() {

    }

    public List<InstantPost> getSecond_layer() {
        return second_layer;
    }

    public void setSecond_layer(List<InstantPost> second_layer) {
        this.second_layer = second_layer;
    }

    public List<InstantPost> getThrid_layer() {
        return thrid_layer;
    }

    public void setThrid_layer(List<InstantPost> thrid_layer) {
        this.thrid_layer = thrid_layer;
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

    @Override
    public String toString() {
        return "RespondForm{" +
                "second_layer=" + second_layer +
                ", thrid_layer=" + thrid_layer +
                ", second_layer_cache=" + Arrays.toString(second_layer_cache) +
                ", third_layer_cache=" + Arrays.toString(third_layer_cache) +
                '}';
    }
}
