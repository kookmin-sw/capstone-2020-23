package com.capstone.moayo.service;

import com.capstone.moayo.entity.Category;
import com.capstone.moayo.entity.Content;

import java.util.List;

public interface DataBindingService {
    public List<Content> requestData(Category category);
}
