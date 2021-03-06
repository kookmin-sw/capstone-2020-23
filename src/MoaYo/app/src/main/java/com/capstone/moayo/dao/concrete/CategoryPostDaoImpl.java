package com.capstone.moayo.dao.concrete;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.capstone.moayo.dao.CategoryPostDao;
import com.capstone.moayo.dao.mapping.CategoryHashTagMapping;
import com.capstone.moayo.dao.mapping.CategoryPostMapping;
import com.capstone.moayo.dao.sqlite.DBHelper;
import com.capstone.moayo.dao.sqlite.StorageInfo;

import java.util.ArrayList;
import java.util.List;

public class CategoryPostDaoImpl implements CategoryPostDao {

    @Override
    public long insert(DBHelper dbHelper, CategoryPostMapping categoryPostMapping) {
        SQLiteDatabase mDB = dbHelper.getWritableDB();
        ContentValues values = new ContentValues();
        values.put(StorageInfo.CreateStorage.CPDOGAMID,categoryPostMapping.getDogamId());
        values.put(StorageInfo.CreateStorage.CPCATEGORYID,categoryPostMapping.getCategoryId());
        values.put(StorageInfo.CreateStorage.CPPOSTID,categoryPostMapping.getPostId());
        mDB.execSQL(StorageInfo.CreateStorage.FOREIGNKEY_ON);
        long result =  mDB.insert(StorageInfo.CreateStorage._CPTABLENAME,null,values);
        mDB.close();
        return result;
    }

    @Override
    public List<CategoryPostMapping> selectByDogamId(DBHelper dbHelper, int dogamId) {
        SQLiteDatabase mDB = dbHelper.getReadableDB();
        Cursor c = mDB.rawQuery("SELECT * FROM "+StorageInfo.CreateStorage._CPTABLENAME +" where co_dogamId = "+dogamId+";",null);
//        c.moveToFirst();
        List<CategoryPostMapping> results = new ArrayList<CategoryPostMapping>();
        while(c.moveToNext()){
            results.add(new CategoryPostMapping(c.getInt(0),c.getInt(1),c.getInt(2)));
        }
        c.close();
        mDB.close();
        return results;
    }

    @Override
    public List<CategoryPostMapping> selectByCategoryId(DBHelper dbHelper, int categoryId) {
        SQLiteDatabase mDB = dbHelper.getReadableDB();
        Cursor c = mDB.rawQuery("SELECT * FROM "+StorageInfo.CreateStorage._CPTABLENAME +" where co_categoryId = "+categoryId+";",null);
//        c.moveToFirst();
        List<CategoryPostMapping> results = new ArrayList<CategoryPostMapping>();
        while(c.moveToNext()){
            results.add(new CategoryPostMapping(c.getInt(0),c.getInt(1),c.getInt(2)));
        }
        c.close();
        mDB.close();
        return results;
    }

    @Override
    public List<CategoryPostMapping> selectByPostId(DBHelper dbHelper, int postId) {
        SQLiteDatabase mDB = dbHelper.getReadableDB();
        Cursor c = mDB.rawQuery("SELECT * FROM "+StorageInfo.CreateStorage._CPTABLENAME +" where co_postId = "+postId+";",null);
//        c.moveToFirst();
        List<CategoryPostMapping> results = new ArrayList<CategoryPostMapping>();
        while(c.moveToNext()){
            results.add(new CategoryPostMapping(c.getInt(0),c.getInt(1),c.getInt(2)));
        }
        c.close();
        mDB.close();
        return results;
    }

    @Override
    public boolean delete(DBHelper dbHelper, int nodeId, int postId) {
        SQLiteDatabase mDB = dbHelper.getWritableDB();
        boolean result = mDB.delete(StorageInfo.CreateStorage._CPTABLENAME, "co_categoryId="+nodeId+" AND co_postId="+postId, null) > 0;
        return result;
    }

    @Override
    public boolean isExist(DBHelper dbHelper, int nodeId, int postId) {
        SQLiteDatabase mDB = dbHelper.getReadableDB();
        Cursor c = mDB.rawQuery("SELECT * FROM " +StorageInfo.CreateStorage._CPTABLENAME+" where co_categoryId="+nodeId+ " AND co_postId="+postId+";", null);
        if(c.getCount() == 0) return false;
        c.moveToFirst();
        return !c.isNull(0);
    }
}
