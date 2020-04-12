package com.capstone.moayo.service;

import com.capstone.moayo.entity.Content;
import com.capstone.moayo.service.dto.ContentDto;

import java.util.List;

public interface ContentService {
    public String createContent(Content content);
    public List<Content> findContentByHashTag(String hashTag);
    public List<ContentDto> findContentByKeyWord(String keyword);
    public void deleteContent(int id);
}
