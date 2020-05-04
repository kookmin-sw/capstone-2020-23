package com.capstone.moayo.dao.concrete;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.capstone.moayo.dao.HashtagDao;
import com.capstone.moayo.dao.mapping.HashTagMapping;
import com.capstone.moayo.dao.sqlite.DBHelper;
import com.capstone.moayo.dao.sqlite.StorageInfo;

import java.util.ArrayList;
import java.util.List;

public class HashtagDaoImpl implements HashtagDao {

    @Override
    public long insert(DBHelper dbHelper, HashTagMapping hashTagMapping) {
        SQLiteDatabase mDB = dbHelper.getWritableDB();
        ContentValues values = new ContentValues();
        values.put(StorageInfo.CreateStorage.HHASHTAG,hashTagMapping.getHashtag());
        mDB.execSQL(StorageInfo.CreateStorage.FOREIGNKEY_ON);
        long result =  mDB.insert(StorageInfo.CreateStorage._HTABLENAME,null,values);
        mDB.close();
        return result;
    }

    @Override
    public boolean delete(DBHelper dbHelper, HashTagMapping hashTagMapping) {
        SQLiteDatabase mDB = dbHelper.getWritableDB();
        mDB.execSQL(StorageInfo.CreateStorage.FOREIGNKEY_ON);
        boolean result = mDB.delete(StorageInfo.CreateStorage._HTABLENAME,"co_hashtag='"+hashTagMapping.getHashtag()+"'",null) > 0;
        mDB.close();
        return result;
    }

    @Override
    public boolean isExist(DBHelper dbHelper, String hashtag) {
        SQLiteDatabase mDB = dbHelper.getReadableDB();
        Cursor c = mDB.rawQuery("SELECT * FROM "+StorageInfo.CreateStorage._HTABLENAME +" where co_hashtag = ''"+hashtag+"'';",null);
        c.moveToFirst();
        return !c.isNull(0);
    }


    @Override
    public List<HashTagMapping> selectAll(DBHelper dbHelper) {
        SQLiteDatabase mDB = dbHelper.getReadableDB();
        Cursor c = mDB.rawQuery("SELECT * FROM "+StorageInfo.CreateStorage._HTABLENAME +";",null);
//        c.moveToFirst();
        List<HashTagMapping> results = new ArrayList<HashTagMapping>();
//        results.add(new HashTagMapping(c.getString(0)));
        while(c.moveToNext()){
            results.add(new HashTagMapping(c.getString(0)));
        }
        c.close();
        mDB.close();

        return results;
    }
}
