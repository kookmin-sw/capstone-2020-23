package com.capstone.moayo.dao.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class DBHelper {

    private static final String DATABASE_NAME = "MoayoStorage(SQLite).db";
    private static final int DATABASE_VERSION = 3;
    private static DatabaseHelper mDBHelper;
    private Context mCtx;

    private class DatabaseHelper extends SQLiteOpenHelper{
        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(StorageInfo.CreateStorage.FOREIGNKEY_ON);
            sqLiteDatabase.execSQL(StorageInfo.CreateStorage._DOGAMCREATE);
            sqLiteDatabase.execSQL(StorageInfo.CreateStorage._CREATE1);
            sqLiteDatabase.execSQL(StorageInfo.CreateStorage._HCREATE);
            sqLiteDatabase.execSQL(StorageInfo.CreateStorage._CREATE0);
            sqLiteDatabase.execSQL(StorageInfo.CreateStorage._CPCREATE);
            sqLiteDatabase.execSQL(StorageInfo.CreateStorage._CHCREATE);
            sqLiteDatabase.execSQL(StorageInfo.CreateStorage._DLCREATE);
        }

        public DatabaseHelper(Context context, String name, CursorFactory factory, int version){
            super(context,name,factory,version);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + StorageInfo.CreateStorage._CPTABLENAME);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + StorageInfo.CreateStorage._CHTABLENAME);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + StorageInfo.CreateStorage._TABLENAME0);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + StorageInfo.CreateStorage._TABLENAME1);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + StorageInfo.CreateStorage._DOGAMTABLENAME);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + StorageInfo.CreateStorage._HTABLENAME);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + StorageInfo.CreateStorage._DOGAMLIKENAME);
            onCreate(sqLiteDatabase);
        }
    }

    public DBHelper(Context context){
        this.mCtx = context;
    }

    public DBHelper init() throws SQLException{
        mDBHelper = new DatabaseHelper(mCtx,DATABASE_NAME,null,DATABASE_VERSION);
        return this;
    }

    public void upgrade(SQLiteDatabase mDB){mDBHelper.onUpgrade(mDB,1,2);}

    public SQLiteDatabase getWritableDB(){
        return mDBHelper.getWritableDatabase();
    }

    public SQLiteDatabase getReadableDB(){
        return mDBHelper.getReadableDatabase();
    }

    public void create(SQLiteDatabase mDB){
        mDBHelper.onCreate(mDB);
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
