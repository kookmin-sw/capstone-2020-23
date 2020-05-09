package com.capstone.moayo.service.concrete;

import android.content.Context;
import android.os.AsyncTask;

import com.capstone.moayo.service.SearchService;
import com.capstone.moayo.service.dto.RequestForm;
import com.capstone.moayo.service.dto.RespondForm;
import com.capstone.moayo.util.retrofit.APIUtils;
import com.capstone.moayo.util.retrofit.SearchAPI;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Response;

public class ConcreteSearchService implements SearchService {
    private SearchAPI searchAPI;

    public ConcreteSearchService(Context context) {
        searchAPI = APIUtils.getSearchAPI();
    }

    @Override
    public RespondForm requestData(RequestForm requsetForm) {
        AsyncTask<RequestForm, Void, RespondForm> thread = new AsyncTask<RequestForm, Void, RespondForm>() {
            @Override
            protected RespondForm doInBackground(RequestForm... requsetForms) {
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
