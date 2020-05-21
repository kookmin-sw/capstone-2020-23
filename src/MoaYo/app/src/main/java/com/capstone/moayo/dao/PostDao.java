package com.capstone.moayo.dao;

import com.capstone.moayo.dao.mapping.HashTagMapping;
import com.capstone.moayo.dao.mapping.PostMapping;
import com.capstone.moayo.dao.sqlite.DBHelper;

import java.util.List;

public interface PostDao {

    public long insert(DBHelper dbHelper, PostMapping postMapping);

    public boolean update(DBHelper dbHelper,PostMapping postMapping);

    public boolean delete(DBHelper dbHelper,int id);
    // 커서 이용한 뒤 close()할것.
    public PostMapping selectById(DBHelper dbHelper, int id);

    public PostMapping selectByUrl(DBHelper dbHelper, String url);

    public List<PostMapping> selectByNodeId(DBHelper dbHelper, int nodeId);

    public boolean isExist(DBHelper dbHelper, PostMapping postMapping);
}
