package com.capstone.moayo.util.retrofit;

public class APIUtils {
    private static final String SEARCH_URL = "";
    private static final String SHARE_URL = "";

    public static SearchAPI getSearchAPI() {
        return RetrofitClient.getClient(SEARCH_URL).create(SearchAPI.class);
    }

    public static ShareAPI getShareAPI() {
        return RetrofitClient.getClient(SHARE_URL).create(ShareAPI.class);
    }
}
