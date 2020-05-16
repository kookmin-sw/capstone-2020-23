package com.capstone.moayo.storage;

import com.capstone.moayo.entity.Category;

import java.util.List;

public interface ShareStorage {
    public String create(Category category);
    public Category retrieveById(int id);
    public List<Category> retrieveAll();
    public String remove(int id);
}
