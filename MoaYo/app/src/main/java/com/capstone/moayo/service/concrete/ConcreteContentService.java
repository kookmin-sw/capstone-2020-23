package com.capstone.moayo.service.concrete;

import com.capstone.moayo.entity.Content;
import com.capstone.moayo.service.ContentService;
import com.capstone.moayo.storage.ContentStorage;

import java.util.List;

public class ConcreteContentService implements ContentService {
    private ContentStorage contentStorage;

    public ConcreteContentService() {

    }

    @Override
    public String createContent(Content content) {
        return null;
    }

    @Override
    public List<Content> findContentByHashtag(String hashtag) {
        return null;
    }

    @Override
    public void deleteContent(int id) {

    }
}
