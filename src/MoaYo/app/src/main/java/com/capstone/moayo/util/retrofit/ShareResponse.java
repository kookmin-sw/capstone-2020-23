package com.capstone.moayo.util.retrofit;

import com.google.gson.annotations.SerializedName;

public class ShareResponse {
    @SerializedName("code")
    int code;
    @SerializedName("dogamId")
    int dogamId;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getDogamId() {
        return dogamId;
    }

    public void setDogamId(int dogamId) {
        this.dogamId = dogamId;
    }
}
