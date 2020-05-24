package com.capstone.moayo.util.retrofit;

import com.capstone.moayo.entity.Model.DogamModel;
import com.capstone.moayo.entity.Model.ModelForm;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ShareAPI {
    @GET("/getDogam")
    Call<ModelForm> requestDogam(@Query("dogamId") int dogamId);

    @POST("/shareDogam")
    Call<Integer> requestCreate(@Body ModelForm form);

    @GET("/getDogamList")
    Call<DogamModel[]> requestDogamAll();

    @GET("/deleteDogam")
    Call<String> requsetDelete(@Query("dogamId") int dogamId);
}
