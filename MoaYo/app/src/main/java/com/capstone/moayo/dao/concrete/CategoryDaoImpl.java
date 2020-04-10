package com.capstone.moayo.dao.concrete;

import android.database.sqlite.SQLiteDatabase;

import com.capstone.moayo.dao.CategoryDao;
import com.capstone.moayo.util.Exception.DaoObjectNullException;

public class CategoryDaoImpl implements CategoryDao {

    private static SQLiteDatabase mDB;
    private volatile static CategoryDao instance;

    private CategoryDaoImpl(){}

    public static synchronized CategoryDao getInstance() throws DaoObjectNullException {
        if(instance == null) {
            synchronized (CategoryDaoImpl.class) {
                if(instance == null) {
                    instance = new CategoryDaoImpl();
                }
            }
        }
        if(mDB == null){
            throw new DaoObjectNullException();
        }
        return instance;
    }

}

