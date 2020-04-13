package com.capstone.moayo.dao.concrete;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.capstone.moayo.dao.DogamDao;
import com.capstone.moayo.dao.sqlite.DBHelper;
import com.capstone.moayo.dao.sqlite.StorageInfo;

public class DogamDaoImpl implements DogamDao {

    private volatile static DogamDao instance;

    public static synchronized DogamDao getInstance(){
        if(instance == null) {
            synchronized (DogamDaoImpl.class) {
                if(instance == null) {
                    instance = new DogamDaoImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public long insert(DBHelper dbHelper, String title, String description, String password) {
        SQLiteDatabase mDB = dbHelper.getWritableDB();
        ContentValues values = new ContentValues();
        values.put(StorageInfo.CreateStorage.DOGAMTITLE,title);
        values.put(StorageInfo.CreateStorage.DOGAMDESCRIPTION,description);
        values.put(StorageInfo.CreateStorage.DOGAMPASSWORD,password);
        long result =  mDB.insert(StorageInfo.CreateStorage._DOTAMTABLENAME,null,values);
        mDB.close();
        return result;
    }

    @Override
    public boolean update(DBHelper dbHelper, int id, String title, String description, String password) {
        SQLiteDatabase mDB = dbHelper.getWritableDB();
        ContentValues values = new ContentValues();
        values.put(StorageInfo.CreateStorage.DOGAMID,id);
        values.put(StorageInfo.CreateStorage.DOGAMTITLE,title);
        values.put(StorageInfo.CreateStorage.DOGAMDESCRIPTION,description);
        values.put(StorageInfo.CreateStorage.DOGAMPASSWORD,password);
        boolean result = mDB.update(StorageInfo.CreateStorage._DOTAMTABLENAME,values,"co_id=" + id,null) > 0;
        mDB.close();
        return result;
    }

    @Override
    public boolean delete(DBHelper dbHelper, int id) {
        SQLiteDatabase mDB = dbHelper.getWritableDB();
        boolean result = mDB.delete(StorageInfo.CreateStorage._DOTAMTABLENAME,"co_id="+id,null) > 0;
        mDB.close();
        return result;
    }

    @Override
    public Cursor select(DBHelper dbHelper, int id) {
        SQLiteDatabase mDB = dbHelper.getReadableDB();
        Cursor c = mDB.rawQuery("SELECT * FROM "+StorageInfo.CreateStorage._DOTAMTABLENAME+" where co_id="+id+";",null);
        mDB.close();
        return c;
    }

    @Override
    public Cursor selectAll(DBHelper dbHelper) {
        SQLiteDatabase mDB = dbHelper.getReadableDB();
        Cursor c = mDB.rawQuery("SELECT * FROM tb_dogamlist ;",null);
        while(c.moveToNext()){
            System.out.print(c.getInt(0));
            System.out.println("/" + c.getString(1));
        }
        c.close();
        mDB.close();
        return c;
    }
}
