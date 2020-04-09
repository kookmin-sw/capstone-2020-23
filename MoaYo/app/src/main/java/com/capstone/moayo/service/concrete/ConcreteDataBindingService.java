package com.capstone.moayo.service.concrete;

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

    public ConcreteDataBindingService() {
        this.dataBindingStorage = StorageFactoryCreator.getInstance().requestDataBindingStorage();
    }

    @Override
    public List<Content> requestData(Category category) {
        JSONObject dogam = new JSONObject();

        try {
            for(CategoryNode parent : category.getRoot().getLowLevel()) {
                String title = parent.getTitle();
                JSONArray lowlevel = new JSONArray();
                for(CategoryNode currentCategory : parent.getLowLevel()) {
                    lowlevel.put(currentCategory.getTitle());
                }
                dogam.put(title, lowlevel);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dataBindingStorage.request(dogam);
    }


}
