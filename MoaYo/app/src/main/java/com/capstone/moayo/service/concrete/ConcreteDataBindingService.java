package com.capstone.moayo.service.concrete;

import android.content.Context;

import com.capstone.moayo.entity.Category;
import com.capstone.moayo.entity.CategoryNode;
import com.capstone.moayo.entity.Content;
import com.capstone.moayo.service.DataBindingService;
import com.capstone.moayo.storage.DataBindingStorage;
import com.capstone.moayo.storage.concrete.StorageFactoryCreator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ConcreteDataBindingService implements DataBindingService {
    private DataBindingStorage dataBindingStorage;

    public ConcreteDataBindingService(Context context) {
        this.dataBindingStorage = StorageFactoryCreator.getInstance().requestDataBindingStorage(context);
    }

    @Override
    public List<Content> requestData(Category category) {
        JSONObject stream = new JSONObject();


//        try {
//            for(CategoryNode node : category.getRoot().getLowLevel()) {
//                JSONArray second_tags = new JSONArray();
//                second_tags.put(node.getTitle());
//                stream.put("second_layer", second_tags);
//                for(CategoryNode node2 : node.getLowLater()) {
//                    JSONArray third_tags = new JSONArray();
//                    third_tags.put(node2.getTitle());
//                    stream.put("third_layer", third_tags);
//                    List<Content> contents = dataBindingStorage.request(stream);
//
//                }
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        return null;
    }


}
