package com.capstone.moayo.storage.concrete;

import android.content.Context;

import com.capstone.moayo.storage.HashtagStorage;

import java.util.List;

public class ConcreteHashtagStorage implements HashtagStorage {

    public ConcreteHashtagStorage(Context context) {

    }

    @Override
    public String create(List<String> hashtagList) {
        return null;
    }

    @Override
    public List<String> retrieveByCategoryId(int id) {
        return null;
    }

    @Override
    public void update(List<String> hashtagList) {

    }

    @Override
    public void remove(String hashtag) {

    }
}
