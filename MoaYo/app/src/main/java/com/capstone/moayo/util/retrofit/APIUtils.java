package com.capstone.moayo.util.retrofit;

public class APIUtils {
    public static final String BASE_URL = "https://instagram.com/p/";

    public static InstagramService getInstagramService() {
        return RetrofitClient.getClient(BASE_URL).create(InstagramService.class);
    }
}
