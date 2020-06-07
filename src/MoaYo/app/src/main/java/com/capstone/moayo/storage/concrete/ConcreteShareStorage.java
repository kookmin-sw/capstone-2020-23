package com.capstone.moayo.storage.concrete;

import android.content.Context;

import com.capstone.moayo.dao.DogamLikeDao;
import com.capstone.moayo.dao.concrete.DaoFactoryCreator;
import com.capstone.moayo.dao.mapping.DogamLikeMapping;
import com.capstone.moayo.dao.sqlite.DBHelper;
import com.capstone.moayo.entity.Model.DogamModel;
import com.capstone.moayo.entity.Model.ModelForm;
import com.capstone.moayo.storage.ShareStorage;
import com.capstone.moayo.util.retrofit.APIUtils;
import com.capstone.moayo.util.retrofit.ShareAPI;
import com.capstone.moayo.util.retrofit.ShareResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class ConcreteShareStorage implements ShareStorage {
    private ShareAPI shareAPI;
    private DogamLikeDao dogamLikeDao;
    private DBHelper dbHelper;

    public ConcreteShareStorage(Context context) {
        this.shareAPI = APIUtils.getShareAPI();
        this.dogamLikeDao = DaoFactoryCreator.getInstance().requestDogamLikeDao();
        this.dbHelper = DaoFactoryCreator.getInstance().initDao(context);
    }

    @Override
    public int create(ModelForm form) {
        int result = 1;
        Call<ShareResponse> call = shareAPI.requestCreate(form);
        try {
            Response<ShareResponse> response = call.execute();
            if(response.body().getCode() == 0) return response.body().getDogamId();
            else return response.body().getCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
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
    public DogamLikeMapping retrieveLiked(int id) {
        DogamLikeMapping mapping = dogamLikeDao.selectById(dbHelper, id);
        return mapping;
    }

    @Override
    public List<DogamModel> retrieveAll() {
        Call<DogamModel[]> call = shareAPI.requestDogamAll();
        try {
            Response<DogamModel[]> response = call.execute();
            List<DogamModel> body = Arrays.asList(response.body());

            return body;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<DogamModel> retrieveByWriter(String writer) {
        Call<DogamModel[]> call = shareAPI.requestDogamByWriter(writer);
        try {
            Response<DogamModel[]> response = call.execute();
            List<DogamModel> body = Arrays.asList(response.body());

            return body;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<DogamModel> retrieveByKeyword(String keyword) {
        Call<DogamModel[]> call = shareAPI.requestDogamByKeyword(keyword);
        try {
            Response<DogamModel[]> response = call.execute();
            List<DogamModel> body = Arrays.asList(response.body());

            return body;
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int remove(int id) {
        int result = 1;
        Call<ShareResponse> call = shareAPI.requsetDelete(id);
        try {
            Response<ShareResponse> response = call.execute();
            result = response.body().getCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int updateLike(int id) {
        int result;
        Call<ShareResponse> call = shareAPI.requestDogamLike(id);
        try {
            Response<ShareResponse> response = call.execute();
            result = response.body().getCode();
            if(result == 0) {
                if (dogamLikeDao.selectById(dbHelper, id) == null)
                    dogamLikeDao.insert(dbHelper, id, true);
                else dogamLikeDao.update(dbHelper, id, true);
            }
            return result;
        }catch (IOException e) {
            e.printStackTrace();
        }
        return 1;
    }

    @Override
    public int updateDisLike(int id) {
        int result;
        Call<ShareResponse> call = shareAPI.requestDogamDisLike(id);
        try {
            Response<ShareResponse> response = call.execute();
            result = response.body().getCode();
            if(result == 0) {
                if (dogamLikeDao.selectById(dbHelper, id) == null)
                    dogamLikeDao.insert(dbHelper, id, false);
                else dogamLikeDao.update(dbHelper, id, false);
            }
            return result;
        }catch (IOException e) {
            e.printStackTrace();
        }
        return 1;
    }
}
