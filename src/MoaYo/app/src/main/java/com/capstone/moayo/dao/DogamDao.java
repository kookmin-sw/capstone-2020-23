package com.capstone.moayo.dao;

import android.database.Cursor;

import com.capstone.moayo.dao.mapping.DogamMapping;
import com.capstone.moayo.dao.sqlite.DBHelper;
import com.capstone.moayo.util.DogamStatus;

public interface DogamDao {

    public long insert(DBHelper dbHelper, String title, String description, String password, DogamStatus status);

    public boolean update(DBHelper dbHelper,int id,String title,String description,String password, DogamStatus status);

    public boolean delete(DBHelper dbHelper,int id);
    // 커서 이용한 뒤 close()할것.
    public DogamMapping selectById(DBHelper dbHelper, int id);

    public DogamMapping selectByTitle(DBHelper dbHelper,String title);

    public DogamMapping[] selectAll(DBHelper dbHelper);
}
