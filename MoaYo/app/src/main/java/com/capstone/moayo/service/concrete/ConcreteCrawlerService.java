package com.capstone.moayo.service.concrete;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.capstone.moayo.service.CrawlerService;
import com.capstone.moayo.service.dto.CategoryNodeDto;
import com.capstone.moayo.service.dto.InstantPost;
import com.capstone.moayo.service.dto.RequsetForm;
import com.capstone.moayo.service.dto.RespondForm;
import com.capstone.moayo.util.CategoryConvertor;
import com.capstone.moayo.util.retrofit.APIUtils;
import com.capstone.moayo.util.retrofit.SearchAPI;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConcreteCrawlerService implements CrawlerService {
    private SearchAPI searchAPI;

    public ConcreteCrawlerService(Context context) {
        searchAPI = APIUtils.getSearchAPI();
    }

    @Override
    public RespondForm requestData(RequsetForm requsetForm) {
        AsyncTask<RequsetForm, Void, RespondForm> thread = new AsyncTask<RequsetForm, Void, RespondForm>() {
            @Override
            protected RespondForm doInBackground(RequsetForm... requsetForms) {
                Call<RespondForm> call = searchAPI.requestPosts(requsetForms[0]);
                try {
                    Response<RespondForm> response = call.execute();
                    RespondForm resultForm = response.body();
                    return resultForm;
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }
        };
        try {
            RespondForm resultForm = thread.execute(requsetForm).get();


            return resultForm;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
