package com.capstone.moayo.dao.concrete;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.capstone.moayo.dao.CategoryDao;
import com.capstone.moayo.dao.sqlite.DBHelper;
import com.capstone.moayo.dao.sqlite.StorageInfo;
import com.capstone.moayo.entity.CategoryNode;
import com.capstone.moayo.util.Exception.DaoObjectNullException;
import com.capstone.moayo.util.DataEntityTranslator;

public class CategoryDaoImpl implements CategoryDao {

    public CategoryDaoImpl(){}

    @Override
    public long insert(DBHelper dbHelper,int level, int parent, String title,int dogamId,int parentDogamId){
        SQLiteDatabase mDB = dbHelper.getWritableDB();
        ContentValues values = new ContentValues();
        values.put(StorageInfo.CreateStorage.CATEGORYLEVEL,level);
        values.put(StorageInfo.CreateStorage.CATEGORYPARENT,parent);
        values.put(StorageInfo.CreateStorage.CATEGORYPARENTDOGAM,parentDogamId);
        values.put(StorageInfo.CreateStorage.CATEGORYTITLE,title);
        values.put(StorageInfo.CreateStorage.CATEGORYDOGAMID,dogamId);
        mDB.execSQL(StorageInfo.CreateStorage.FOREIGNKEY_ON);
        long result =  mDB.insert(StorageInfo.CreateStorage._TABLENAME0,null,values);

        mDB.execSQL("UPDATE " + StorageInfo.CreateStorage._TABLENAME0 + " set " + StorageInfo.CreateStorage.CATEGORYID +" = "
        + result +" where " + StorageInfo.CreateStorage.CATEGORYDOGAMID + " = " + dogamId + " and " + StorageInfo.CreateStorage.CATEGORYID
        + " IS NULL;");
        mDB.close();
        return result;
    }

    @Override
    public long rootInsert(DBHelper dbHelper, int level, int parent, String title, int dogamId, int parentDogamId) {
        SQLiteDatabase mDB = dbHelper.getWritableDB();
        ContentValues values = new ContentValues();
        values.put(StorageInfo.CreateStorage.CATEGORYLEVEL,level);
        values.put(StorageInfo.CreateStorage.CATEGORYPARENT,parent);
        values.put(StorageInfo.CreateStorage.CATEGORYPARENTDOGAM,parentDogamId);
        values.put(StorageInfo.CreateStorage.CATEGORYTITLE,title);
        values.put(StorageInfo.CreateStorage.CATEGORYDOGAMID,dogamId);
        long result =  mDB.insert(StorageInfo.CreateStorage._TABLENAME0,null,values);
        mDB.execSQL("UPDATE " + StorageInfo.CreateStorage._TABLENAME0 + " set " + StorageInfo.CreateStorage.CATEGORYID +" = "
                + result +", "+StorageInfo.CreateStorage.CATEGORYPARENT +" = "+result +" where " + StorageInfo.CreateStorage.CATEGORYDOGAMID + " = " + dogamId + " and " + StorageInfo.CreateStorage.CATEGORYID
                + " IS NULL;");
        mDB.close();
        return result;
    }

    @Override
    public boolean update(DBHelper dbHelper,int id,int level,int parent, String title,int dogamId,int parentDogamId){
        SQLiteDatabase mDB = dbHelper.getWritableDB();
        ContentValues values = new ContentValues();
        values.put(StorageInfo.CreateStorage.CATEGORYID,id);
        values.put(StorageInfo.CreateStorage.CATEGORYLEVEL,level);
        values.put(StorageInfo.CreateStorage.CATEGORYPARENT,parent);
        values.put(StorageInfo.CreateStorage.CATEGORYPARENTDOGAM,parentDogamId);
        values.put(StorageInfo.CreateStorage.CATEGORYTITLE,title);
        values.put(StorageInfo.CreateStorage.CATEGORYDOGAMID,dogamId);
        mDB.execSQL(StorageInfo.CreateStorage.FOREIGNKEY_ON);
        boolean result = mDB.update(StorageInfo.CreateStorage._TABLENAME0,values,"co_id=" + id,null) > 0;
        mDB.close();
        return result;
    }

    @Override
    public boolean rootUpdate(DBHelper dbHelper, int id, int level, int parent, String title, int dogamId, int parentDogamId) {
        SQLiteDatabase mDB = dbHelper.getWritableDB();
        ContentValues values = new ContentValues();
        values.put(StorageInfo.CreateStorage.CATEGORYID,id);
        values.put(StorageInfo.CreateStorage.CATEGORYLEVEL,level);
        values.put(StorageInfo.CreateStorage.CATEGORYPARENT,parent);
        values.put(StorageInfo.CreateStorage.CATEGORYPARENTDOGAM,parentDogamId);
        values.put(StorageInfo.CreateStorage.CATEGORYTITLE,title);
        values.put(StorageInfo.CreateStorage.CATEGORYDOGAMID,dogamId);
        boolean result = mDB.update(StorageInfo.CreateStorage._TABLENAME0,values,"co_dogamId=" + dogamId + " AND co_id = " + id ,null) > 0;
        mDB.close();
        return result;
    }

    @Override
    public boolean delete(DBHelper dbHelper,int id){
        SQLiteDatabase mDB = dbHelper.getWritableDB();
        mDB.execSQL(StorageInfo.CreateStorage.FOREIGNKEY_ON);
        boolean result = mDB.delete(StorageInfo.CreateStorage._TABLENAME0,"co_id="+id,null) > 0;
        mDB.close();
        return result;
    }
    // 커서 이용한 뒤 close()할것.
    @Override
    public CategoryNode selectByTitle(DBHelper dbHelper, String title){
        SQLiteDatabase mDB = dbHelper.getReadableDB();
        Cursor c = mDB.rawQuery("SELECT * FROM "+StorageInfo.CreateStorage._TABLENAME0+" where co_title='"+title+"';",null);
        CategoryNode result = new CategoryNode();
        c.moveToFirst();
        result.setId(c.getInt(0));
        result.setTitle(c.getString(1));
        result.setLevel(c.getInt(3));
        c.close();
        mDB.close();
        return result;
    }

    @Override
    public CategoryNode selectByDogamId(DBHelper dbHelper,int dogamId) {
        SQLiteDatabase mDB = dbHelper.getReadableDB();
        Cursor c = mDB.rawQuery("SELECT * FROM "+StorageInfo.CreateStorage._TABLENAME0+" where co_dogamId = '"+dogamId+"';",null);
        DataEntityTranslator det = new DataEntityTranslator();
        CategoryNode result = det.cursorToCategoryNode(c);
        mDB.close();
        return result;
    }
}

