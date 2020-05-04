package com.capstone.moayo.dao.concrete;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.capstone.moayo.dao.CategoryHashtagDao;
import com.capstone.moayo.dao.mapping.CategoryHashTagMapping;
import com.capstone.moayo.dao.sqlite.DBHelper;
import com.capstone.moayo.dao.sqlite.StorageInfo;

import java.util.ArrayList;
import java.util.List;

public class CategoryHashtagDaoImpl implements CategoryHashtagDao {

    @Override
    public long insert(DBHelper dbHelper, CategoryHashTagMapping categoryHashTagMapping) {
        SQLiteDatabase mDB = dbHelper.getWritableDB();
        ContentValues values = new ContentValues();
        values.put(StorageInfo.CreateStorage.CHDOGAMID,categoryHashTagMapping.getDogamId());
        values.put(StorageInfo.CreateStorage.CHCATEGORYID,categoryHashTagMapping.getCategoryId());
        values.put(StorageInfo.CreateStorage.CHHASHTAG,categoryHashTagMapping.getHashtag());
        mDB.execSQL(StorageInfo.CreateStorage.FOREIGNKEY_ON);
        long result =  mDB.insert(StorageInfo.CreateStorage._CHTABLENAME,null,values);
        mDB.close();
        return result;
    }

    @Override
    public List<CategoryHashTagMapping> selectByDogamId(DBHelper dbHelper, int dogamId) {
        SQLiteDatabase mDB = dbHelper.getReadableDB();
        Cursor c = mDB.rawQuery("SELECT * FROM "+StorageInfo.CreateStorage._CHTABLENAME +" where co_dogamId = "+dogamId+";",null);
//        c.moveToFirst();
        List<CategoryHashTagMapping> results = new ArrayList<CategoryHashTagMapping>();
        while(c.moveToNext()){
            results.add(new CategoryHashTagMapping(c.getInt(0),c.getInt(1),c.getString(2)));
        }
        c.close();
        mDB.close();
        return results;
    }

    @Override
    public List<CategoryHashTagMapping> selectByCategoryId(DBHelper dbHelper, int categoryId) {
        SQLiteDatabase mDB = dbHelper.getReadableDB();
        Cursor c = mDB.rawQuery("SELECT * FROM "+StorageInfo.CreateStorage._CHTABLENAME +" where co_categoryId = "+categoryId+";",null);
//        c.moveToFirst();
        List<CategoryHashTagMapping> results = new ArrayList<CategoryHashTagMapping>();
        while(c.moveToNext()){
            results.add(new CategoryHashTagMapping(c.getInt(0),c.getInt(1),c.getString(2)));
        }
        c.close();
        mDB.close();
        return results;
    }

    @Override
    public List<CategoryHashTagMapping> selectByHashtag(DBHelper dbHelper, String hashtag) {
        SQLiteDatabase mDB = dbHelper.getReadableDB();
        Cursor c = mDB.rawQuery("SELECT * FROM "+StorageInfo.CreateStorage._CHTABLENAME +" where co_hashtag = '"+hashtag+"';",null);
//        c.moveToFirst();
        List<CategoryHashTagMapping> results = new ArrayList<CategoryHashTagMapping>();
        while(c.moveToNext()){
            results.add(new CategoryHashTagMapping(c.getInt(0),c.getInt(1),c.getString(2)));
        }
        c.close();
        mDB.close();
        return results;
    }
}
