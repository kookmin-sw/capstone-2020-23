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

        String[] secondCache = new String[form.getSecond_layer().size()];
        String[] thirdCache = new String[form.getThird_layer().size()];

        for(int i = 0; i < secondCache.length; i++) secondCache[i] = "";
        for(int i = 0; i < thirdCache.length; i++) thirdCache[i] = "";

        return form;
    }

    public static String convertCategoryToJSON(CategoryNodeDto categoryNodeDto) {
        try {
            JSONObject rootObject = new JSONObject();
            CategoryNodeDto parentNode = categoryNodeDto.getParent();
            JSONArray firstArray = new JSONArray(parentNode.getHashtags());
            rootObject.put("second_layer", firstArray);
            JSONArray secondArray = new JSONArray(categoryNodeDto.getHashtags());
            rootObject.put("third_layer", secondArray);

            return rootObject.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}

