package com.capstone.moayo.storage.concrete;

import android.content.Context;
import android.util.Log;

import com.capstone.moayo.dao.CategoryDao;
import com.capstone.moayo.dao.DogamDao;
import com.capstone.moayo.dao.concrete.DaoFactoryCreator;
import com.capstone.moayo.entity.Model.ModelForm;
import com.capstone.moayo.storage.ShareStorage;
import com.capstone.moayo.util.retrofit.APIUtils;
import com.capstone.moayo.util.retrofit.ShareAPI;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class ConcreteShareStorage implements ShareStorage {
    private ShareAPI shareAPI;

    public ConcreteShareStorage(Context context) {
        this.shareAPI = APIUtils.getShareAPI();
    }

    @Override
    public String create(ModelForm form) {
        Call<Void> call = shareAPI.requestCreate(form);
        try {
            call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ModelForm retrieveById(int id) {
        Call<ModelForm> call = shareAPI.requestDogam(id);
        try {
            Response<ModelForm> response = call.execute();
            ModelForm form = response.body();
            if(form != null)
                return form;
            else
                return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ModelForm> retrieveAll() {
        return null;
    }

    @Override
    public String remove(int id) {
        return null;
    }
}
