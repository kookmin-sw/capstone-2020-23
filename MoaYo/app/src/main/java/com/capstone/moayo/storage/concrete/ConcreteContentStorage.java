package com.capstone.moayo.storage.concrete;

import android.content.Context;

import com.capstone.moayo.service.dto.ContentDto;
import com.capstone.moayo.storage.CategoryStorage;
import com.capstone.moayo.storage.ContentStorage;

import java.util.List;

public class ConcreteContentStorage implements ContentStorage {
    @Override
    public String createContent() {
        return null;
    }

    @Override
    public List<ContentDto> findContentByNodeId() {
        return null;
    }

    @Override
    public List<ContentDto> findContentByDogamId() {
        return null;
    }

    @Override
    public ContentDto findContentById() {
        return null;
    }

    @Override
    public void modifyContent() {

    }

    @Override
    public void removeContent() {

    }

    public ConcreteContentStorage(Context context) {
    }
}
