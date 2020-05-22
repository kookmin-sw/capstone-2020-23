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
        int dogamId = (int) dogamDao.insert(dbHelper, category.getTitle(), category.getDescription(), category.getPassword(), category.getStatus());

        category.setId(dogamId);
        categoryMap.put(dogamId, category);
        return dogamId;
    }

    @Override
    public DogamMapping retrieveById(int id) {
        DogamMapping foundDogam = null;
        if(categoryMap.containsKey(id)) {
            Category foundCategory = categoryMap.get(id);
            foundDogam = new DogamMapping();
            foundDogam.setId(foundCategory.getId());
            foundDogam.setTitle(foundCategory.getTitle());
            foundDogam.setDesription(foundCategory.getDescription());
            foundDogam.setPassword(foundCategory.getPassword());
            foundDogam.setStatus(foundCategory.getStatus());
        } else {
            foundDogam = dogamDao.selectById(dbHelper, id);
            Category category = new Category(foundDogam.getTitle(), foundDogam.getDesription(), foundDogam.getPassword(), null);
            category.setStatus(foundDogam.getStatus());
            categoryMap.put(category.getId(), category);
        }
        return foundDogam;
    }

    @Override
    public Collection<Category> retrieveAll() {
        if(categoryMap.isEmpty()) {
            DogamMapping[] dogamMappings = dogamDao.selectAll(dbHelper);
            for (DogamMapping dogam : dogamMappings) {
                Category category = new Category(dogam.getTitle(), dogam.getDesription(), dogam.getPassword(), null);
                category.setId(dogam.getId());

                if(!categoryMap.containsKey(category.getId())) categoryMap.put(category.getId(), category);
            }
        }
        return categoryMap.values();
    }

    @Override
    public void update(Category category) {
        try {
            boolean result = dogamDao.update(dbHelper, category.getId(), category.getTitle(), category.getDescription(), category.getPassword(), category.getStatus());
            if(result != true)
                throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean remove(int id) {
        boolean result = dogamDao.delete(dbHelper, id);
        return result;
    }
}
