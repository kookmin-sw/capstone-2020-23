package com.capstone.moayo.dao.concrete;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.capstone.moayo.dao.DogamLikeDao;
import com.capstone.moayo.dao.mapping.DogamLikeMapping;
import com.capstone.moayo.dao.sqlite.DBHelper;
import com.capstone.moayo.dao.sqlite.StorageInfo;

import java.util.ArrayList;
import java.util.List;

public class DogamLikeDaoImpl implements DogamLikeDao {
    @Override
    public long insert(DBHelper dbHelper, int dogamId, boolean isLiked) {
        SQLiteDatabase mDB = dbHelper.getWritableDB();
        ContentValues values = new ContentValues();
        values.put(StorageInfo.CreateStorage.LIKEID, dogamId);
        if(isLiked)
            values.put(StorageInfo.CreateStorage.ISLIKED, 0);
        else
            values.put(StorageInfo.CreateStorage.ISLIKED, 1);

        long result = mDB.insert(StorageInfo.CreateStorage._DOGAMLIKENAME, null, values);
        mDB.close();
        return result;
    }

    @Override
    public DogamLikeMapping selectById(DBHelper dbHelper, int dogamId) {
        SQLiteDatabase mDB = dbHelper.getReadableDB();
        Cursor c = mDB.rawQuery("SELECT * FROM " + StorageInfo.CreateStorage._DOGAMLIKENAME + " WHERE co_dogamId= " + dogamId + ";", null);
        Log.i("cursor info", Integer.toString(c.getCount()));
        if(c.getCount() == 0) return null;
        c.moveToFirst();
        DogamLikeMapping mapping = new DogamLikeMapping(c.getInt(0), c.getInt(1));
        c.close();
        mDB.close();
        return mapping;
    }

    @Override
    public DogamLikeMapping[] selectAll(DBHelper dbHelper) {
        SQLiteDatabase mDB = dbHelper.getReadableDB();
        Cursor c = mDB.rawQuery("SELECT * FROM " + StorageInfo.CreateStorage._DOGAMLIKENAME + ";", null);
        List<DogamLikeMapping> list = new ArrayList<>();
        while(c.moveToNext()) {
            DogamLikeMapping mapping = new DogamLikeMapping(c.getInt(0), c.getInt(1));
            list.add(mapping);
        }
        mDB.close();
        return (DogamLikeMapping[]) list.toArray();
    }

    @Override
    public boolean update(DBHelper dbHelper, int dogamId, boolean isLiked) {
        SQLiteDatabase mDB = dbHelper.getWritableDB();
        ContentValues values = new ContentValues();
        values.put(StorageInfo.CreateStorage.LIKEID, dogamId);
        if(isLiked)
            values.put(StorageInfo.CreateStorage.ISLIKED, 0);
        else
            values.put(StorageInfo.CreateStorage.ISLIKED, 1);
        boolean result = mDB.update(StorageInfo.CreateStorage._DOGAMLIKENAME, values, "co_dogamId = " + dogamId, null) > 0;
        mDB.close();
        return result;
    }

    @Override
    public boolean delete(DBHelper dbHelper, int dogamId) {
        SQLiteDatabase mDB = dbHelper.getWritableDB();
        boolean result = mDB.delete(StorageInfo.CreateStorage._DOGAMLIKENAME, "co_dogamId="+dogamId, null) > 0;
        mDB.close();
        return result;
    }
}
