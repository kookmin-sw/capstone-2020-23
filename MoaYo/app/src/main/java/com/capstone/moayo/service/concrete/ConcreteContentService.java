package com.capstone.moayo.service.concrete;

import android.content.Context;

import com.capstone.moayo.entity.Post;
import com.capstone.moayo.service.ContentService;
import com.capstone.moayo.service.dto.PostDto;
import com.capstone.moayo.storage.ContentStorage;

import java.util.List;

public class ConcreteContentService implements ContentService {
    private ContentStorage contentStorage;

    public ConcreteContentService(Context context) {

    }

    @Override
    public String createContent(Post content) {
        return null;
    }

    @Override
    public List<Post> findContentByHashTag(String hashTag) {
        return null;
    }

    @Override
    public List<PostDto> findContentByKeyWord(String keyword) {
        return null;
    }

    @Override
    public void deleteContent(int id) {

    }
}
