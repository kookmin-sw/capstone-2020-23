package com.capstone.moayo.dao.concrete;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.capstone.moayo.dao.PostDao;
import com.capstone.moayo.dao.mapping.HashTagMapping;
import com.capstone.moayo.dao.mapping.PostMapping;
import com.capstone.moayo.dao.sqlite.DBHelper;
import com.capstone.moayo.dao.sqlite.StorageInfo;
import com.capstone.moayo.entity.Post;
import com.capstone.moayo.util.Exception.DaoObjectNullException;

import java.util.List;

public class PostDaoImpl implements PostDao {

    // if insert new data, id = null;
    @Override
    public long insert(DBHelper dbHelper,PostMapping postMapping){
        SQLiteDatabase mDB = dbHelper.getWritableDB();
        mDB.execSQL(StorageInfo.CreateStorage.FOREIGNKEY_ON);
        ContentValues values = new ContentValues();
        values.put(StorageInfo.CreateStorage.URL,postMapping.getUrl());
        values.put(StorageInfo.CreateStorage.IMGURL,postMapping.getImgUrl());
        values.put(StorageInfo.CreateStorage.HASHTAG,postMapping.getHashTag());
        values.put(StorageInfo.CreateStorage.LIKE, postMapping.getLike());
        long result =  mDB.insert(StorageInfo.CreateStorage._TABLENAME1,null,values);
        mDB.close();
        return result;
    }
    @Override
    public boolean update(DBHelper dbHelper,PostMapping postMapping){
        SQLiteDatabase mDB = dbHelper.getWritableDB();
        ContentValues values = new ContentValues();
        mDB.execSQL(StorageInfo.CreateStorage.FOREIGNKEY_ON);
        values.put(StorageInfo.CreateStorage.CID,postMapping.getId());
        values.put(StorageInfo.CreateStorage.URL,postMapping.getUrl());
        values.put(StorageInfo.CreateStorage.IMGURL,postMapping.getImgUrl());
        values.put(StorageInfo.CreateStorage.HASHTAG,postMapping.getHashTag());
        boolean result = mDB.update(StorageInfo.CreateStorage._TABLENAME1,values,"co_postId=" + postMapping.getId(),null) > 0;
        mDB.close();
        return result;
    }
    @Override
    public boolean delete(DBHelper dbHelper,int id){
        SQLiteDatabase mDB = dbHelper.getWritableDB();
        mDB.execSQL(StorageInfo.CreateStorage.FOREIGNKEY_ON);
        boolean result = mDB.delete(StorageInfo.CreateStorage._TABLENAME1,"co_postId="+id,null) > 0;
        mDB.close();
        return result;
    }

    @Override
    public PostMapping selectById(DBHelper dbHelper, int id) {
        SQLiteDatabase mDB = dbHelper.getReadableDB();
        Cursor c = mDB.rawQuery("SELECT * FROM "+StorageInfo.CreateStorage._TABLENAME1+" where co_postId="+id+";",null);
        c.moveToFirst();

        PostMapping cm = new PostMapping(c.getInt(0),c.getString(1),c.getString(2),c.getString(3), c.getInt(4));
        c.close();
        mDB.close();
        return cm;
    }

    @Override
    public PostMapping selectByUrl(DBHelper dbHelper, String url) {
        SQLiteDatabase mDB = dbHelper.getReadableDB();
        Cursor c = mDB.rawQuery("SELECT * FROM "+StorageInfo.CreateStorage._TABLENAME1+" where co_url='"+url+"';", null);

        c.moveToFirst();

        PostMapping postMapping = new PostMapping(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getInt(4));
        c.close();
        mDB.close();
        return postMapping;
    }

    @Override
    public List<PostMapping> selectByNodeId(DBHelper dbHelper, int nodeId) {
        return null;
    }

    @Override
    public boolean isExist(DBHelper dbHelper, PostMapping postMapping) {
        SQLiteDatabase mDB = dbHelper.getReadableDB();
        Cursor c = mDB.rawQuery("SELECT * FROM "+ StorageInfo.CreateStorage._TABLENAME1 + " where co_url='"+postMapping.getUrl()+"';", null);
        c.moveToFirst();
        if (c.getCount() == 0) return false;
        return !c.isNull(0);
    }
}
