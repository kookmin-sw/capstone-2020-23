package com.capstone.moayo.service;

import com.capstone.moayo.entity.Category;
import com.capstone.moayo.entity.Post;
import com.capstone.moayo.service.dto.CategoryNodeDto;
import com.capstone.moayo.service.dto.RespondForm;

import java.util.List;

public interface DataBindingService {
    public RespondForm requestData(CategoryNodeDto secondLayer, CategoryNodeDto thirdLayer);
}
