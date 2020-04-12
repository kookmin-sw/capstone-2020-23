package com.capstone.moayo.util.retrofit;

import com.capstone.moayo.entity.Content;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface InstagramService {
    @GET("/api/v1/topsearch?query={querys}")
    Call<List<String>> listResult(@Path("querys") String querys);

    @POST("/api/v1/crawl")
    Call<List<Content>> listContents();

}
