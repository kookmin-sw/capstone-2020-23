package com.capstone.moayo.storage.concrete;

import android.content.Context;
import android.util.Log;

import com.capstone.moayo.MainActivity;
import com.capstone.moayo.dao.CategoryDao;
import com.capstone.moayo.dao.concrete.CategoryDaoImpl;
import com.capstone.moayo.dao.sqlite.DBHelper;
import com.capstone.moayo.entity.Category;
import com.capstone.moayo.entity.CategoryNode;
import com.capstone.moayo.entity.Content;
import com.capstone.moayo.storage.CategoryStorage;
import com.capstone.moayo.storage.StorageFactory;
import com.capstone.moayo.util.Exception.DaoObjectNullException;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ConcreteCategoryStorage implements CategoryStorage {
    private CategoryDao categoryDao;
    private DBHelper dbHelper;

    public ConcreteCategoryStorage(Context context) {
        try {
            dbHelper = StorageFactoryCreator.getInstance().initDao(context);
            categoryDao = CategoryDaoImpl.getInstance();
        } catch (DaoObjectNullException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String create(Category category) {
//        CategoryNode rootNode = category.getRootNode();
//        //1st node
//        categoryDao.insert(dbHelper, rootNode.getLevel(), 0, rootNode.getTitle());
//        for(CategoryNode secondNode : rootNode.getLowLayer()) {
//            categoryDao.insert(dbHelper, secondNode.getLevel(), secondNode.getParent().getId(), secondNode.getTitle());
//            for(CategoryNode thirdNode : secondNode.getLowLayer()) {
//                categoryDao.insert(dbHelper, thirdNode.getLevel(), thirdNode.getParent().getId(),thirdNode.getTitle());
//            }
//        }
//        Gson gson = new Gson();
//
//        String result = gson.toJson(category);
//        Log.d("category", result);
        return "Success";
//        JSONObject object = new JSONObject();
//        try {
//            CategoryNode categoryNode = category.getRootNode();
//
//            object.put("title", categoryNode.getTitle());
//            object.put("parent", categoryNode.getParent());
//            object.put("level", categoryNode.getLevel());
//            JSONArray secondObject = new JSONArray();
//            for(CategoryNode secondNode : categoryNode.getLowLayer()) {
//                JSONObject secondNodeObject = new JSONObject();
//                secondNodeObject.put("title", secondNode.getTitle());
//                secondNodeObject.put("parent", secondNode.getParent());
//                secondNodeObject.put("level", secondNode.getLevel());
//                JSONArray thirdObject = new JSONArray();
//                for(CategoryNode thirdNode : secondNode.getLowLayer()) {
//                    JSONObject thirdNodeObject = new JSONObject();
//                    thirdNodeObject.put("title", thirdNode.getTitle());
//                    thirdNodeObject.put("parent", thirdNode.getParent());
//                    thirdNodeObject.put("level", thirdNode.getLevel());
//                    thirdObject.put(thirdNodeObject.toString());
//                }
//                secondObject.put(secondNodeObject.toString());
//            }
//            object.put("layer", secondObject);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        Log.d("category", object.toString());
//        return object.toString();
    }

    @Override
    public List<Category> retrieveByTitle(String title) {
        return null;
    }

    @Override
    public Category retrieveById(int id) {
        return null;
    }

    @Override
    public String update(Category category) {
        return null;
    }

    @Override
    public String remove(int id) {
        return null;
    }
}
