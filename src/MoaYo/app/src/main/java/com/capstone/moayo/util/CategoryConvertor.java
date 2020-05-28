package com.capstone.moayo.util;

import com.capstone.moayo.service.dto.CategoryNodeDto;
import com.capstone.moayo.service.dto.RequestForm;

import org.json.JSONArray;
import org.json.JSONObject;

public class CategoryConvertor {
    public static RequestForm generateForm(CategoryNodeDto secondNode, CategoryNodeDto thirdNode) {
        RequestForm form = new RequestForm();

        form.setSecond_layer(secondNode.getHashtags());
        form.setThird_layer(thirdNode.getHashtags());

        if(secondNode.getCache().size() == 0) {
            String[] secondCache = new String[secondNode.getHashtags().size()];
            for(int i = 0; i < secondCache.length; i++) secondCache[i] = "";
            form.setSecond_layer_cache(secondCache);
        } else {
            form.setSecond_layer_cache((String[]) secondNode.getCache().toArray());
        }

        if(thirdNode.getCache().size() == 0) {
            String[] thirdCache = new String[thirdNode.getHashtags().size()];
            for(int i = 0; i < thirdCache.length; i++) thirdCache[i] = "";
            form.setThird_layer_cache(thirdCache);
        } else {
            form.setSecond_layer_cache((String[]) secondNode.getCache().toArray());
        }

        return form;
    }

}

