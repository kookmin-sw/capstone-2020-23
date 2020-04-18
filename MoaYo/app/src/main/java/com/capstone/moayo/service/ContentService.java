package com.capstone.moayo.service;

import com.capstone.moayo.entity.Post;
import com.capstone.moayo.service.dto.PostDto;

import java.util.List;

public interface ContentService {
    public String createContent(Post content);
    public List<Post> findContentByHashTag(String hashTag);
    public List<PostDto> findContentByKeyWord(String keyword);
    public void deleteContent(int id);
}
