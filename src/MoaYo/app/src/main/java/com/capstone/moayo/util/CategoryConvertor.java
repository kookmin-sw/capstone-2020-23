package com.capstone.moayo.util;

import android.util.Log;

import com.capstone.moayo.service.dto.CategoryNodeDto;
import com.capstone.moayo.service.dto.RequestForm;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public class CategoryConvertor {
    public static RequestForm generateForm(CategoryNodeDto secondNode, CategoryNodeDto thirdNode, Map<Integer, List<String>> cacheMap) {
        RequestForm form = new RequestForm();

        form.setSecond_layer(secondNode.getHashtags());
        form.setThird_layer(thirdNode.getHashtags());

        if(cacheMap.get(secondNode.getId()).size() == 0) {
            String[] secondCache = new String[secondNode.getHashtags().size()];
            for(int i = 0; i < secondCache.length; i++) secondCache[i] = "";
            form.setSecond_layer_cache(secondCache);
        } else {
            form.setSecond_layer_cache((String[]) cacheMap.get(0).toArray());
        }

        if(cacheMap.get(thirdNode.getId()).size() == 0) {
            String[] thirdCache = new String[thirdNode.getHashtags().size()];
            for(int i = 0; i < thirdCache.length; i++) thirdCache[i] = "";
            form.setThird_layer_cache(thirdCache);
        } else {
            form.setThird_layer_cache((String[]) cacheMap.get(1).toArray());
        }
        Log.d("generate requestForm", form.toString());
        return form;
    }

}

