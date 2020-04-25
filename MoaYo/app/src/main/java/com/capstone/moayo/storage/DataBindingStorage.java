package com.capstone.moayo.storage;

import com.capstone.moayo.entity.Post;

import org.json.JSONObject;

import java.util.List;

public interface DataBindingStorage {
    public List<Post> request(JSONObject hashtags);
}
