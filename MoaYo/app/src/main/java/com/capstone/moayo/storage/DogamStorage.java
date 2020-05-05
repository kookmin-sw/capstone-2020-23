package com.capstone.moayo.storage;

import com.capstone.moayo.dao.mapping.DogamMapping;
import com.capstone.moayo.entity.Category;

public interface DogamStorage {
    public int create(Category category);
    public DogamMapping retrieveById(int id);
    public void update(Category category);
    public void remove(Category category);
}
