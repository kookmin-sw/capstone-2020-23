package com.capstone.moayo.util.retrofit;

public class APIUtils {
    private static final String SEARCH_URL = "http://192.168.0.22:8080";
    private static final String SHARE_URL = "http://58.122.92.72:8080";

    public static SearchAPI getSearchAPI() {
        return RetrofitClient.getClient(SEARCH_URL).create(SearchAPI.class);
    }

    public static ShareAPI getShareAPI() {
        return RetrofitClient.getClient(SHARE_URL).create(ShareAPI.class);
    }
}
