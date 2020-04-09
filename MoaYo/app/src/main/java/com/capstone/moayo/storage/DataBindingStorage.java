package com.capstone.moayo.storage;

import com.capstone.moayo.entity.Content;

import org.json.JSONObject;

import java.util.List;

public interface DataBindingStorage {
    public List<Content> request(JSONObject hashtags);
}
