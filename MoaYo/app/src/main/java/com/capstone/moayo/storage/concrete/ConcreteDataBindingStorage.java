package com.capstone.moayo.storage.concrete;

import android.content.Context;
import android.util.Log;

import com.capstone.moayo.entity.Post;
import com.capstone.moayo.storage.DataBindingStorage;
import com.capstone.moayo.util.retrofit.InstagramAPI;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ConcreteDataBindingStorage implements DataBindingStorage {
    private InstagramAPI instagramService;

    public ConcreteDataBindingStorage(Context context) {
//        instagramService = APIUtils.getInstagramService();
    }

    @Override
    public List<Post> request(JSONObject dogam) {

        List<Post> contents = new ArrayList<>();
        
        Log.d("dogam info", dogam.toString());
        return null;
    }

//    private Map<String, List<String>> JSONToObject(JSONObject dogam) {
//
//    }
}
