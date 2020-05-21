package com.capstone.moayo.util.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit search_retrofit = null;
    private static Retrofit share_retrofit = null;

    public static Retrofit getSearchClient(String baseUrl) {
        if(search_retrofit == null) {
            search_retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return search_retrofit;
    }

    public static Retrofit getShareClient(String baseUrl) {
        if(share_retrofit == null) {
            share_retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return share_retrofit;
    }
}
