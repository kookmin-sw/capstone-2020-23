package com.capstone.moayo.dao.concrete;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.capstone.moayo.dao.DogamDao;
import com.capstone.moayo.dao.mapping.DogamMapping;
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
        long result =  mDB.insert(StorageInfo.CreateStorage._DOGAMTABLENAME,null,values);
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
        boolean result = mDB.update(StorageInfo.CreateStorage._DOGAMTABLENAME,values,"co_id=" + id,null) > 0;
        mDB.close();
        return result;
    }

    @Override
    public boolean delete(DBHelper dbHelper, int id) {
        SQLiteDatabase mDB = dbHelper.getWritableDB();
        mDB.execSQL(StorageInfo.CreateStorage.FOREIGNKEY_ON);
        boolean result = mDB.delete(StorageInfo.CreateStorage._DOGAMTABLENAME,"co_id="+id,null) > 0;
        mDB.close();
        return result;
    }

    @Override
    public DogamMapping selectById(DBHelper dbHelper, int id) {
        SQLiteDatabase mDB = dbHelper.getReadableDB();
        Cursor c = mDB.rawQuery("SELECT * FROM "+StorageInfo.CreateStorage._DOGAMTABLENAME +" where co_id='"+id+"';",null);
        c.moveToFirst();
        DogamMapping dm = new DogamMapping();
        dm.setId(c.getInt(0));
        dm.setTitle(c.getString(1));
        dm.setDesription(c.getString(2));
        dm.setPassword(c.getString(3));
        c.close();
        mDB.close();

        return dm;
    }

    @Override
    public DogamMapping selectByTitle(DBHelper dbHelper, String title) {
        SQLiteDatabase mDB = dbHelper.getReadableDB();
        Cursor c = mDB.rawQuery("SELECT * FROM "+StorageInfo.CreateStorage._DOGAMTABLENAME +" where co_title='"+title+"';",null);
        c.moveToFirst();
        DogamMapping dm = new DogamMapping();
        dm.setId(c.getInt(0));
        dm.setTitle(c.getString(1));
        dm.setDesription(c.getString(2));
        dm.setPassword(c.getString(3));
        c.close();
        mDB.close();

        return dm;
    }

    @Override
    public DogamMapping[] selectAll(DBHelper dbHelper) {
        SQLiteDatabase mDB = dbHelper.getReadableDB();
        Cursor c = mDB.rawQuery("SELECT * FROM "+StorageInfo.CreateStorage._DOGAMTABLENAME +" ;",null);
        c.moveToFirst();
        DogamMapping[] result = new DogamMapping[c.getCount()];
        for(int i = 0;i<c.getCount();i++){
            result[i] = new DogamMapping();
            result[i].setId(c.getInt(0));
            result[i].setTitle(c.getString(1));
            result[i].setDesription(c.getString(2));
            result[i].setPassword(c.getString(3));
            c.moveToNext();
        }
        c.close();
        mDB.close();

        return result;
    }
}
