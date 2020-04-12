package com.capstone.moayo.dao.concrete;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.capstone.moayo.dao.ContentDao;
import com.capstone.moayo.dao.sqlite.DBHelper;
import com.capstone.moayo.dao.sqlite.StorageInfo;
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
        return instance;
    }

    public long insert(DBHelper dbHelper, int parent,int level,String url,String imgUrl,String info,String hashTag){
        SQLiteDatabase mDB = dbHelper.getWritableDB();
        ContentValues values = new ContentValues();
        values.put(StorageInfo.CreateStorage.CPARENT,parent);
        values.put(StorageInfo.CreateStorage.CLEVEL,level);
        values.put(StorageInfo.CreateStorage.URL,url);
        values.put(StorageInfo.CreateStorage.IMGURL,imgUrl);
        values.put(StorageInfo.CreateStorage.INFO,info);
        values.put(StorageInfo.CreateStorage.HASHTAG,hashTag);
        long result =  mDB.insert(StorageInfo.CreateStorage._TABLENAME1,null,values);
        mDB.close();
        return result;
    }

    public boolean update(DBHelper dbHelper,int id, int parent,int level,String url,String imgUrl,String info,String hashTag){
        SQLiteDatabase mDB = dbHelper.getWritableDB();
        ContentValues values = new ContentValues();
        values.put(StorageInfo.CreateStorage.CID,id);
        values.put(StorageInfo.CreateStorage.CPARENT,parent);
        values.put(StorageInfo.CreateStorage.CLEVEL,level);
        values.put(StorageInfo.CreateStorage.URL,url);
        values.put(StorageInfo.CreateStorage.IMGURL,imgUrl);
        values.put(StorageInfo.CreateStorage.INFO,info);
        values.put(StorageInfo.CreateStorage.HASHTAG,hashTag);
        boolean result = mDB.update(StorageInfo.CreateStorage._TABLENAME1,values,"co_id=" + id,null) > 0;
        mDB.close();
        return result;
    }

    public boolean delete(DBHelper dbHelper,int id){
        SQLiteDatabase mDB = dbHelper.getWritableDB();
        boolean result = mDB.delete(StorageInfo.CreateStorage._TABLENAME1,"co_id="+id,null) > 0;
        mDB.close();
        return result;
    }
    // 커서 이용한 뒤 close()할것.
    public Cursor select(DBHelper dbHelper, int id){
        SQLiteDatabase mDB = dbHelper.getReadableDB();
        Cursor c = mDB.rawQuery("SELECT * FROM "+StorageInfo.CreateStorage._TABLENAME1+" where co_id="+id+";",null);
        mDB.close();
        return c;
    }
}
