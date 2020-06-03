package com.capstone.moayo.storage;

import android.content.Context;

import com.capstone.moayo.dao.mapping.CategoryMapping;
import com.capstone.moayo.entity.Category;
import com.capstone.moayo.entity.CategoryNode;

import java.util.List;

public interface CategoryStorage {
    public String create(Category category);
    public CategoryNode retrieveById(int dogamId, int id);
    public CategoryNode retrieveByDogamId(int id);
    public String update(Category category);
    public String remove(int dogamId, int id);
    public void init();
}
