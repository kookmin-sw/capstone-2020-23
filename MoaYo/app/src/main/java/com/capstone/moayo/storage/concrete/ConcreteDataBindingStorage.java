package com.capstone.moayo.storage.concrete;

import android.os.Debug;
import android.util.Log;

import com.capstone.moayo.entity.Content;
import com.capstone.moayo.storage.DataBindingStorage;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConcreteDataBindingStorage implements DataBindingStorage {

    public ConcreteDataBindingStorage() {

    }

    @Override
    public List<Content> request(JSONObject dogam) {

        List<Content> contents = new ArrayList<>();
        Log.d("dogam info", dogam.toString());
        return null;
    }

//    private Map<String, List<String>> JSONToObject(JSONObject dogam) {
//
//    }
}
