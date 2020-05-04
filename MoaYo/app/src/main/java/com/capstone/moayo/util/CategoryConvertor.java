package com.capstone.moayo.util;

import com.capstone.moayo.entity.CategoryNode;
import com.capstone.moayo.service.dto.CategoryNodeDto;

import org.json.JSONArray;
import org.json.JSONObject;

public class CategoryConvertor {
    public static String convertCategoryToJSON(CategoryNodeDto secondNode, CategoryNodeDto thirdNode) {
        try {
            JSONObject rootObject = new JSONObject();

            JSONArray secondArray = new JSONArray(secondNode.getHashtags());
            rootObject.put("second_layer", secondArray);
            JSONArray thirdArray = new JSONArray(thirdNode.getHashtags());
            rootObject.put("third_layer", thirdArray);

            return rootObject.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
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
