package com.capstone.moayo.storage.concrete;

import android.content.Context;
import android.util.Log;

import com.capstone.moayo.dao.DogamDao;
import com.capstone.moayo.dao.concrete.DaoFactoryCreator;
import com.capstone.moayo.dao.mapping.DogamMapping;
import com.capstone.moayo.dao.sqlite.DBHelper;
import com.capstone.moayo.entity.Category;
import com.capstone.moayo.storage.DogamStorage;
import com.capstone.moayo.storage.map.MemoryMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ConcreteDogamStorage implements DogamStorage {
    private DogamDao dogamDao;
    private DBHelper dbHelper;

    private Map<Integer, Category> categoryMap;

    public ConcreteDogamStorage(Context context) {
        dogamDao = DaoFactoryCreator.getInstance().requestDogamDao();
        dbHelper = DaoFactoryCreator.getInstance().initDao(context);

        categoryMap = MemoryMap.getInstance().getCategoryMap();
    }

    @Override
    public int create(Category category) {
        int dogamId = (int) dogamDao.insert(dbHelper, category.getTitle(), category.getDescription(), category.getPassword(), category.getUrl(), category.getStatus());

        category.setId(dogamId);
        categoryMap.put(dogamId, category);
        return dogamId;
    }

    @Override
    public Category retrieveById(int id) {
        Category foundCategory = null;
        if(categoryMap.containsKey(id)) {
            foundCategory = categoryMap.get(id);
        } else {
            DogamMapping foundDogam = dogamDao.selectById(dbHelper, id);
             foundCategory = new Category(foundDogam.getTitle(), foundDogam.getDescription(), foundDogam.getPassword(), null);
            foundCategory.setStatus(foundDogam.getStatus());
            foundCategory.setUrl(foundDogam.getUrl());
            categoryMap.put(foundCategory.getId(), foundCategory);
        }
        return foundCategory;
    }

    @Override
    public Collection<Category> retrieveAll() {
        if(categoryMap.isEmpty()) {
            List<Category> categories = new ArrayList<>();
            DogamMapping[] dogamMappings = dogamDao.selectAll(dbHelper);
            for (DogamMapping dogam : dogamMappings) {
                Category category = new Category(dogam.getTitle(), dogam.getDescription(), dogam.getPassword(), null);
                category.setId(dogam.getId());
                category.setUrl(dogam.getUrl());

                categories.add(category);
                if(!categoryMap.containsKey(category.getId())) categoryMap.put(category.getId(), category);
            }
        }
        return categoryMap.values();
    }

    @Override
    public void update(Category category) {
        try {
            boolean result = dogamDao.update(dbHelper, category.getId(), category.getTitle(), category.getDescription(), category.getPassword(), category.getUrl(), category.getStatus());
            if(result != true)
                throw new Exception();
            categoryMap.replace(category.getId(), category);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean remove(int id) {
        boolean result = dogamDao.delete(dbHelper, id);
        if(result)
            categoryMap.remove(id);
        return result;
    }
}
