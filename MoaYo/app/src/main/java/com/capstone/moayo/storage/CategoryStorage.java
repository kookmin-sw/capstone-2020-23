package com.capstone.moayo.storage;

import com.capstone.moayo.entity.Category;

import java.util.List;

public interface CategoryStorage {
    public String create(Category category);
    public List<Category> retrieveByTitle(String title);
    public Category retrieveById(int id);
    public String update(Category category);
    public String remove(int id);
}
