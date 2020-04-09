package com.capstone.moayo.service;

import com.capstone.moayo.entity.Content;

import java.util.List;

public interface ContentService {
    public String createContent(Content content);
    public List<Content> findContentByHashtag(String hashtag);
    public void deleteContent(int id);
}
