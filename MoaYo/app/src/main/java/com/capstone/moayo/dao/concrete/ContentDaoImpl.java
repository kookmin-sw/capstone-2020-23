package com.capstone.moayo.dao.concrete;

import android.database.sqlite.SQLiteDatabase;

import com.capstone.moayo.dao.ContentDao;
import com.capstone.moayo.entity.Content;
import com.capstone.moayo.util.Exception.DaoObjectNullException;

public class ContentDaoImpl implements ContentDao {
    private static SQLiteDatabase mDB;
    private volatile static ContentDao instance;

    private ContentDaoImpl(){}

    public static synchronized ContentDao getInstance() throws DaoObjectNullException {
        if(instance == null) {
            synchronized (ContentDaoImpl.class) {
                if(instance == null) {
                    instance = new ContentDaoImpl();
                }
            }
        }
        if(mDB == null){
            throw new DaoObjectNullException();
        }
        return instance;
    }

}
