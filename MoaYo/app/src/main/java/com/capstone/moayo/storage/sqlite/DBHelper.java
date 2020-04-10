package com.capstone.moayo.storage.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper {

    private static final String DATABASE_NAME = "MoayoStorage(SQLite).db";
    private static final int DATABASE_VERSION = 1;
    private DatabaseHelper mDBHelper;
    private static SQLiteDatabase mDB;
    private Context mCtx;

    private class DatabaseHelper extends SQLiteOpenHelper{
        public DatabaseHelper(Context context, String name, CursorFactory factory, int version){
            super(context,name,factory,version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(StorageInfo.CreateStorage._CREATE0);
            sqLiteDatabase.execSQL(StorageInfo.CreateStorage._CREATE1);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + StorageInfo.CreateStorage._TABLENAME0);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + StorageInfo.CreateStorage._TABLENAME1);
            onCreate(sqLiteDatabase);
        }
    }

    public DBHelper(Context context){
        this.mCtx = context;
    }

    public DBHelper open() throws SQLException{
        mDBHelper = new DatabaseHelper(mCtx,DATABASE_NAME,null,DATABASE_VERSION);
        mDB = mDBHelper.getWritableDatabase();
        return this;
    }

    public void create(){
        mDBHelper.onCreate(mDB);
    }

    public void close(){
        mDB.close();
    }
//
//    // Insert DB
//    public long insertColumn(String userid, String name, long age , String gender){
//        ContentValues values = new ContentValues();
//        values.put(DataBases.CreateDB.USERID, userid);
//        values.put(DataBases.CreateDB.NAME, name);
//        values.put(DataBases.CreateDB.AGE, age);
//        values.put(DataBases.CreateDB.GENDER, gender);
//        return mDB.insert(DataBases.CreateDB._TABLENAME0, null, values);
//    }
//
//    // Update DB
//    public boolean updateColumn(long id, String userid, String name, long age , String gender){
//        ContentValues values = new ContentValues();
//        values.put(DataBases.CreateDB.USERID, userid);
//        values.put(DataBases.CreateDB.NAME, name);
//        values.put(DataBases.CreateDB.AGE, age);
//        values.put(DataBases.CreateDB.GENDER, gender);
//        return mDB.update(DataBases.CreateDB._TABLENAME0, values, "_id=" + id, null) > 0;
//    }
//
//    // Delete All
//    public void deleteAllColumns() {
//        mDB.delete(DataBases.CreateDB._TABLENAME0, null, null);
//    }
//
//    // Delete DB
//    public boolean deleteColumn(long id){
//        return mDB.delete(DataBases.CreateDB._TABLENAME0, "_id="+id, null) > 0;
//    }
//    // Select DB
//    public Cursor selectColumns(){
//        return mDB.query(DataBases.CreateDB._TABLENAME0, null, null, null, null, null, null);
//    }
//
//    // sort by column
//    public Cursor sortColumn(String sort){
//        Cursor c = mDB.rawQuery( "SELECT * FROM usertable ORDER BY " + sort + ";", null);
//        return c;
//    }
}
