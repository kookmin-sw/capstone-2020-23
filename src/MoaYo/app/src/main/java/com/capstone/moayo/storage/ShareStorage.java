package com.capstone.moayo.storage;

import com.capstone.moayo.entity.Category;
import com.capstone.moayo.entity.Model.DogamModel;
import com.capstone.moayo.entity.Model.ModelForm;

import java.util.List;

public interface ShareStorage {
    public int create(ModelForm form);
    public ModelForm retrieveById(int id);
    public List<DogamModel> retrieveAll();
    public String remove(int id);
}
