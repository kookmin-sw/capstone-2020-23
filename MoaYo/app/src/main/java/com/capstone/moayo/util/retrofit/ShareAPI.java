package com.capstone.moayo.util.retrofit;

import com.capstone.moayo.entity.Model.ModelForm;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ShareAPI {
    @GET("/api/v1/share/load?query={query}")
    Call<ModelForm> requestDogam(@Query("query") int dogamId);

    @POST("/api/v1/share/create")
    Call<Void> requestCreate(@Body ModelForm form);
}
