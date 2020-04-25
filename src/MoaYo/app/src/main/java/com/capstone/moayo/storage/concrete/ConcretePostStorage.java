package com.capstone.moayo.storage.concrete;

import android.content.Context;

import com.capstone.moayo.service.dto.PostDto;
import com.capstone.moayo.storage.PostStorage;

import java.util.List;

public class ConcretePostStorage implements PostStorage {
    @Override
    public String createContent() {
        return null;
    }

    @Override
    public List<PostDto> findContentByNodeId() {
        return null;
    }

    @Override
    public List<PostDto> findContentByDogamId() {
        return null;
    }

    @Override
    public PostDto findContentById() {
        return null;
    }

    @Override
    public void modifyContent() {

    }

    @Override
    public void removeContent() {

    }

    public ConcretePostStorage(Context context) {
    }
}
