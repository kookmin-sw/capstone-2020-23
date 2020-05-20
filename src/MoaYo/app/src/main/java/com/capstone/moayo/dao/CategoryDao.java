package com.capstone.moayo.dao;

import android.database.Cursor;

import com.capstone.moayo.dao.sqlite.DBHelper;
import com.capstone.moayo.entity.CategoryNode;

public interface CategoryDao {
    public long insert(DBHelper dbHelper,int level, int parent, String title,int dogamId);

    public boolean update(DBHelper dbHelper,int id,int level,int parent, String title,int dogamId);

    public boolean delete(DBHelper dbHelper,int id);
    // 커서 이용한 뒤 close()할것.
    public CategoryNode selectByTitle(DBHelper dbHelper, String title);

    public CategoryNode selectByDogamId(DBHelper dbHelper, int dogamId);
}
