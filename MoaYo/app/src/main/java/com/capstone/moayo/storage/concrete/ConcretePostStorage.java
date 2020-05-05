package com.capstone.moayo.storage.concrete;

import android.content.Context;

import com.capstone.moayo.service.dto.PostDto;
import com.capstone.moayo.storage.PostStorage;

import java.util.List;

public class ConcretePostStorage implements PostStorage {

    public ConcretePostStorage(Context context) {

    }

    @Override
    public String createPost() {
        return null;
    }

    @Override
    public List<PostDto> findPostByNodeId() {
        return null;
    }

    @Override
    public List<PostDto> findPostByDogamId() {
        return null;
    }

    @Override
    public PostDto findPostById() {
        return null;
    }

    @Override
    public void modifyPost() {

    }

    @Override
    public void removePost() {

    }
}
