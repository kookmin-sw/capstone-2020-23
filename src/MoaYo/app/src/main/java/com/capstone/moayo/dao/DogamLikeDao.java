package com.capstone.moayo.dao;

import com.capstone.moayo.dao.mapping.DogamLikeMapping;
import com.capstone.moayo.dao.sqlite.DBHelper;

public interface DogamLikeDao {
    long insert(DBHelper dbHelper, int dogamId, boolean isLiked);
    DogamLikeMapping selectById(DBHelper dbHelper, int dogamId);
    DogamLikeMapping[] selectAll(DBHelper dbHelper);
    boolean update(DBHelper dbHelper, int dogamId, boolean isLiked);
    boolean delete(DBHelper dbHelper, int dogamId);
}
