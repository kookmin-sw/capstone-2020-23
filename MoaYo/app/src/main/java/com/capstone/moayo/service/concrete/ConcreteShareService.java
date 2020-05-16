package com.capstone.moayo.service.concrete;

import android.os.AsyncTask;
import android.os.SharedMemory;

import com.capstone.moayo.entity.Model.ModelForm;
import com.capstone.moayo.service.ShareService;
import com.capstone.moayo.service.dto.CategoryDto;
import com.capstone.moayo.util.ShareUtil;
import com.capstone.moayo.util.retrofit.APIUtils;
import com.capstone.moayo.util.retrofit.ShareAPI;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Response;

public class ConcreteShareService implements ShareService {
    private ShareAPI shareAPI;

    public ConcreteShareService() {
        this.shareAPI = APIUtils.getShareAPI();
    }

    @Override
    public void registerDogam(CategoryDto categoryDto) {

    }

    @Override
    public CategoryDto loadDogam(int dogamId) {
        AsyncTask<Integer, Void, ModelForm> thread = new AsyncTask<Integer, Void, ModelForm>() {
            @Override
            protected ModelForm doInBackground(Integer... integers) {
                int dogamId = integers[0];
                Call<ModelForm> call = shareAPI.requestDogam(dogamId);
                try {
                    Response<ModelForm> response = call.execute();
                    ModelForm form = response.body();
                    return form;
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }
        };

        try {
            ModelForm form = thread.execute(dogamId).get();
            CategoryDto dogam = ShareUtil.convertFormToDogam(form);
            return dogam;
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CategoryDto> loadAll() {
        return null;
    }

    @Override
    public void deleteDogam(int dogamId) {

    }
}
