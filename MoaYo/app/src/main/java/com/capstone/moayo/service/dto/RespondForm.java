package com.capstone.moayo.service.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RespondForm {
    @SerializedName("second_layer")
    private List<InstantPost> second_layer;
    @SerializedName("third_layer")
    private List<InstantPost> thrid_layer;

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
}
