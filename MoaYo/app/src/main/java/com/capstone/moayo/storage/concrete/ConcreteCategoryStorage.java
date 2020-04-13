package com.capstone.moayo.storage.concrete;

import android.content.Context;
import android.util.Log;

import com.capstone.moayo.MainActivity;
import com.capstone.moayo.dao.CategoryDao;
import com.capstone.moayo.dao.DogamDao;
import com.capstone.moayo.dao.concrete.CategoryDaoImpl;
import com.capstone.moayo.dao.concrete.DogamDaoImpl;
import com.capstone.moayo.dao.mapping.DogamMapping;
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
    private DogamDao dogamDao;
    private DBHelper dbHelper;

    public ConcreteCategoryStorage(Context context) {
        try {
            dbHelper = StorageFactoryCreator.getInstance().initDao(context);
            categoryDao = CategoryDaoImpl.getInstance();
            dogamDao = DogamDaoImpl.getInstance();
        } catch (DaoObjectNullException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String create(Category category) {
        //Insert Category Info using dogamDao in DogamTable
        dogamDao.insert(dbHelper, category.getTitle(), category.getDescription(), category.getPassword());
        CategoryNode rootNode = category.getRootNode();
        categoryDao.insert(dbHelper, rootNode.getLevel(), 0, rootNode.getTitle(), category.getId());
        for(CategoryNode secondNode : rootNode.getLowLayer()) {
            categoryDao.insert(dbHelper, secondNode.getLevel(), secondNode.getParent().getId(), secondNode.getTitle(), category.getId());
            for(CategoryNode thirdNode : secondNode.getLowLayer()) {
                categoryDao.insert(dbHelper, thirdNode.getLevel(), thirdNode.getParent().getId(),thirdNode.getTitle(), category.getId());
            }
        }
        Log.d("Category", category.toString());
        return "Success";
    }

    @Override
    public List<Category> retrieveByTitle(String title) {
        return null;
    }

    @Override
    public Category retrieveById(int id) {
        DogamMapping mapping = dogamDao.selectById(dbHelper, id);
        CategoryNode foundNode = categoryDao.selectByDogamId(dbHelper, id);
        Category foundCategory = new Category(mapping.getTitle(), mapping.getDesription(), mapping.getPassword(), foundNode);
        return foundCategory;
    }

    @Override
    public String update(Category category) {
        boolean c_result = dogamDao.update(dbHelper, category.getId(), category.getTitle(), category.getDescription(), category.getPassword());
        if(c_result) {
            CategoryNode rootNode = category.getRootNode();
            c_result = categoryDao.update(dbHelper, rootNode.getId(), rootNode.getLevel(), 0, rootNode.getTitle(), category.getId());
            if(c_result) {
                for(CategoryNode secondNode : rootNode.getLowLayer()) {
                    c_result = categoryDao.update(dbHelper, secondNode.getId(), secondNode.getLevel(), rootNode.getId(), secondNode.getTitle(), category.getId());
                    if(c_result) {
                        for (CategoryNode thirdNode : secondNode.getLowLayer()) {
                            c_result =categoryDao.update(dbHelper, thirdNode.getId(), thirdNode.getLevel(), secondNode.getId(), thirdNode.getTitle(), category.getId());
                            if(!c_result) return "fail to update";
                        }
                    } else return "fail to update";
                }
            } else return "fail to update";

        } else return "fail to update";
        return "success to update";
    }

    @Override
    public String remove(int id) {
        boolean result = categoryDao.delete(dbHelper, id);
        if(result)
            return "success to delete";
        else
            return "fail to delete";
    }
}
