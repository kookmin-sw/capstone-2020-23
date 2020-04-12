package com.capstone.moayo.dao;

import android.database.Cursor;

import com.capstone.moayo.dao.sqlite.DBHelper;

public interface CategoryDao {
    public long insert(DBHelper dbHelper,int level, int parent, String title);

    public boolean update(DBHelper dbHelper,int id,int level,int parent, String title);

    public boolean delete(DBHelper dbHelper,int id);
    // 커서 이용한 뒤 close()할것.
    public Cursor select(DBHelper dbHelper, int id);

    public Cursor selectAll(DBHelper dbHelper);
}
