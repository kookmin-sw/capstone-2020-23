package com.capstone.moayo.util.retrofit;

public class APIUtils {
    private static final String SEARCH_URL = "http://ec2-13-125-96-172.ap-northeast-2.compute.amazonaws.com:3000";
    private static final String SHARE_URL = "http://192.168.35.66:8080";

    public static SearchAPI getSearchAPI() {
        return RetrofitClient.getSearchClient(SEARCH_URL).create(SearchAPI.class);
    }

    public static ShareAPI getShareAPI() {
        return RetrofitClient.getShareClient(SHARE_URL).create(ShareAPI.class);
    }
}
