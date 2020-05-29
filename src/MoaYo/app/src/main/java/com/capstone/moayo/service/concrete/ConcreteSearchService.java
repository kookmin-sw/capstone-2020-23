package com.capstone.moayo.service.concrete;

import android.content.Context;
import android.util.Log;

import com.capstone.moayo.service.SearchService;
import com.capstone.moayo.service.dto.CategoryNodeDto;
import com.capstone.moayo.service.dto.InstantPost;
import com.capstone.moayo.service.dto.RequestForm;
import com.capstone.moayo.service.dto.RespondForm;
import com.capstone.moayo.util.CategoryConvertor;
import com.capstone.moayo.util.retrofit.APIUtils;
import com.capstone.moayo.util.retrofit.SearchAPI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

public class ConcreteSearchService implements SearchService {
    private SearchAPI searchAPI;
    private Map<Integer, List<String>> cacheMap;

    public ConcreteSearchService(Context context) {
        searchAPI = APIUtils.getSearchAPI();
        cacheMap = new LinkedHashMap<>();
    }

    @Override
    public List<InstantPost> requestData(CategoryNodeDto firstNode, CategoryNodeDto secondNode) {
        if(!cacheMap.containsKey(firstNode.getId()) && !cacheMap.containsKey(secondNode.getId())) {
            cacheMap.put(firstNode.getId(), new ArrayList<>());
            cacheMap.put(secondNode.getId(), new ArrayList<>());
        }

        RequestForm form = CategoryConvertor.generateForm(firstNode, secondNode, cacheMap);
        Log.d("convert result", form.toString());
        Call<RespondForm> call = searchAPI.requestPosts(form);
        try {
            Response<RespondForm> response = call.execute();
            Log.d("reqeust header", response.headers().toString());
            RespondForm resultForm = response.body();
            cacheMap.put(firstNode.getId(), Arrays.asList(resultForm.getSecond_layer_cache()));
            cacheMap.put(secondNode.getId(), Arrays.asList(resultForm.getThird_layer_cache()));
            //Log.d("request result", resultForm.getThrid_layer().toString());
            for(String cache : resultForm.getThird_layer_cache())
                Log.d("request cache", cache);
            return resultForm.getThrid_layer();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void initCache() {
        cacheMap.clear();
    }
}
