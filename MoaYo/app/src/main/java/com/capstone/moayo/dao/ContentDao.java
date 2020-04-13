package com.capstone.moayo.dao;

import android.database.Cursor;

import com.capstone.moayo.dao.mapping.ContentMapping;
import com.capstone.moayo.dao.sqlite.DBHelper;

public interface ContentDao {

    public long insert(DBHelper dbHelper, int parent,int level,String url,String imgUrl,String info,String hashTag);

    public boolean update(DBHelper dbHelper,int id, int parent,int level,String url,String imgUrl,String info,String hashTag);

    public boolean delete(DBHelper dbHelper,int id);
    // 커서 이용한 뒤 close()할것.
    public ContentMapping selectById(DBHelper dbHelper, int id);

    public ContentMapping[] selectByparent(DBHelper dbHelper, int parent);
}
