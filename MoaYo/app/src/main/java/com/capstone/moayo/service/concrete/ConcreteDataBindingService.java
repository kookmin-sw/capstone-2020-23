package com.capstone.moayo.service.concrete;

import android.content.Context;

import com.capstone.moayo.entity.Category;
import com.capstone.moayo.entity.Post;
import com.capstone.moayo.service.DataBindingService;
import com.capstone.moayo.service.dto.CategoryNodeDto;
import com.capstone.moayo.service.dto.RespondForm;
import com.capstone.moayo.storage.concrete.StorageFactoryCreator;
import com.capstone.moayo.util.CategoryConvertor;
import com.capstone.moayo.util.retrofit.APIUtils;
import com.capstone.moayo.util.retrofit.RetrofitClient;
import com.capstone.moayo.util.retrofit.SearchAPI;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ConcreteDataBindingService implements DataBindingService {
    private SearchAPI searchAPI;

    public ConcreteDataBindingService(Context context) {
        searchAPI = APIUtils.getSearchAPI();
    }

    @Override
    public RespondForm requestData(CategoryNodeDto secondLayer, CategoryNodeDto thirdLayer) {
        String requestForm = CategoryConvertor.convertCategoryToJSON(secondLayer, thirdLayer);
        Call<RespondForm> call = searchAPI.listContents(requestForm);
        try {
            Response<RespondForm> responseForm = call.execute();
            return responseForm.body();
        } catch (IOException e) {

        }
        return null;
    }
}
