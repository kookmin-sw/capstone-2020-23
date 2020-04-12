package com.capstone.moayo.dao;

import com.capstone.moayo.dao.sqlite.DBHelper;

public interface CategoryDao {
    public long insert(DBHelper dbHelper, int level, int parent, String title);

}
