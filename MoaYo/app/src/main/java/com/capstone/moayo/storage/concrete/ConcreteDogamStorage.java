package com.capstone.moayo.storage.concrete;

import android.content.Context;

import com.capstone.moayo.dao.DaoFactory;
import com.capstone.moayo.dao.DogamDao;
import com.capstone.moayo.dao.concrete.DaoFactoryCreator;
import com.capstone.moayo.dao.mapping.DogamMapping;
import com.capstone.moayo.dao.sqlite.DBHelper;
import com.capstone.moayo.entity.Category;
import com.capstone.moayo.storage.DogamStorage;

public class ConcreteDogamStorage implements DogamStorage {
    private DogamDao dogamDao;
    private DBHelper dbHelper;

    public ConcreteDogamStorage(Context context) {
        dogamDao = DaoFactoryCreator.getInstance().requestDogamDao();
        dbHelper = DaoFactoryCreator.getInstance().initDao(context);
    }

    @Override
    public int create(Category category) {
        int dogamId = (int) dogamDao.insert(dbHelper, category.getTitle(), category.getDescription(), category.getPassword());
        return dogamId;
    }

    @Override
    public DogamMapping retrieveById(int id) {
        DogamMapping foundDogam = dogamDao.selectById(dbHelper, id);
        return foundDogam;
    }

    @Override
    public void update(Category category) {

    }

    @Override
    public void remove(Category category) {

    }
}
