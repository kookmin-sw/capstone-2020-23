package com.capstone.moayo.service;

import com.capstone.moayo.entity.Category;
import com.capstone.moayo.entity.Post;

import java.util.List;

public interface DataBindingService {
    public List<Post> requestData(Category category);
}
