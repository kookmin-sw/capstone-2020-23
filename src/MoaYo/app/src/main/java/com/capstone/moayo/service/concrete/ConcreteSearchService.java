package com.capstone.moayo.service.concrete;

import android.content.Context;
import com.capstone.moayo.service.SearchService;
import com.capstone.moayo.service.dto.CategoryNodeDto;
import com.capstone.moayo.service.dto.RequestForm;
import com.capstone.moayo.service.dto.RespondForm;
import com.capstone.moayo.util.CategoryConvertor;
import com.capstone.moayo.util.retrofit.APIUtils;
import com.capstone.moayo.util.retrofit.SearchAPI;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Response;

public class ConcreteSearchService implements SearchService {
    private SearchAPI searchAPI;

    public ConcreteSearchService(Context context) {
        searchAPI = APIUtils.getSearchAPI();
    }

    @Override
    public RespondForm requestData(CategoryNodeDto firstNode, CategoryNodeDto secondNode) {
        RequestForm form = CategoryConvertor.generateForm(firstNode, secondNode);
        Call<RespondForm> call = searchAPI.requestPosts(form);
        try {
            Response<RespondForm> response = call.execute();
            RespondForm resultForm = response.body();
            firstNode.setCache(Arrays.asList(resultForm.getSecond_layer_cache()));
            secondNode.setCache(Arrays.asList(resultForm.getThird_layer_cache()));
            return resultForm;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
