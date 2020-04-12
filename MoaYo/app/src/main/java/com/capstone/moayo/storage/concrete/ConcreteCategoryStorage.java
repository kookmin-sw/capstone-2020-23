package com.capstone.moayo.storage.concrete;

import android.content.Context;

import com.capstone.moayo.MainActivity;
import com.capstone.moayo.dao.sqlite.DBHelper;
import com.capstone.moayo.entity.Category;
import com.capstone.moayo.storage.CategoryStorage;
import com.capstone.moayo.storage.StorageFactory;

import java.util.List;

public class ConcreteCategoryStorage implements CategoryStorage {

    public ConcreteCategoryStorage(Context context) {
        StorageFactoryCreator.getInstance().initDao(context);
    }

    @Override
    public String create(Category category) {

        return "it's fine";
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
