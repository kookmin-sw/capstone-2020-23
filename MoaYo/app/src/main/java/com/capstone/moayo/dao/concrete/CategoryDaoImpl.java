package com.capstone.moayo.dao.concrete;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.capstone.moayo.dao.CategoryDao;
import com.capstone.moayo.dao.sqlite.DBHelper;
import com.capstone.moayo.dao.sqlite.StorageInfo;
import com.capstone.moayo.util.Exception.DaoObjectNullException;

public class CategoryDaoImpl implements CategoryDao {

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
        return instance;
    }

    public long insert(DBHelper dbHelper,int level, int parent, String title){
        SQLiteDatabase mDB = dbHelper.getWritableDB();
        ContentValues values = new ContentValues();
        values.put(StorageInfo.CreateStorage.CATEGORYLEVEL,level);
        values.put(StorageInfo.CreateStorage.CATEGORYPARENT,parent);
        values.put(StorageInfo.CreateStorage.CATEGORYTITLE,title);
        long result =  mDB.insert(StorageInfo.CreateStorage._TABLENAME0,null,values);
        mDB.close();
        return result;
    }

    public boolean update(DBHelper dbHelper,int id,int level,int parent, String title){
        SQLiteDatabase mDB = dbHelper.getWritableDB();
        ContentValues values = new ContentValues();
        values.put(StorageInfo.CreateStorage.CATEGORYID,id);
        values.put(StorageInfo.CreateStorage.CATEGORYLEVEL,level);
        values.put(StorageInfo.CreateStorage.CATEGORYPARENT,parent);
        values.put(StorageInfo.CreateStorage.CATEGORYTITLE,title);
        boolean result = mDB.update(StorageInfo.CreateStorage._TABLENAME0,values,"co_id=" + id,null) > 0;
        mDB.close();
        return result;
    }

    public boolean delete(DBHelper dbHelper,int id){
        SQLiteDatabase mDB = dbHelper.getWritableDB();
        boolean result = mDB.delete(StorageInfo.CreateStorage._TABLENAME0,"co_id="+id,null) > 0;
        mDB.close();
        return result;
    }
    // 커서 이용한 뒤 close()할것.
    public Cursor select(DBHelper dbHelper, int id){
        SQLiteDatabase mDB = dbHelper.getReadableDB();
        Cursor c = mDB.rawQuery("SELECT * FROM "+StorageInfo.CreateStorage._TABLENAME0+" where co_id="+id+";",null);
        mDB.close();
        return c;
    }

    @Override
    public Cursor selectAll(DBHelper dbHelper) {
        SQLiteDatabase mDB = dbHelper.getReadableDB();
        Cursor c = mDB.rawQuery("SELECT * FROM tb_category ;",null);
        while(c.moveToNext()){
            System.out.print(c.getInt(0));
            System.out.println("/" + c.getString(1));
        }
        c.close();
        mDB.close();
        return c;
    }
}

