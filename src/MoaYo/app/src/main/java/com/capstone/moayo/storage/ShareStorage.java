package com.capstone.moayo.storage;

import com.capstone.moayo.dao.mapping.DogamLikeMapping;
import com.capstone.moayo.entity.Category;
import com.capstone.moayo.entity.Model.DogamModel;
import com.capstone.moayo.entity.Model.ModelForm;

import java.util.List;

public interface ShareStorage {
    public int create(ModelForm form);
    public ModelForm retrieveById(int id);
    DogamLikeMapping retrieveLiked(int id);
    public List<DogamModel> retrieveAll();
    List<DogamModel> retrieveByWriter(String writer);
    List<DogamModel> retrieveByKeyword(String keyword);
    public int remove(int id);
    int updateLike(int id);
    int updateDisLike(int id);

}
