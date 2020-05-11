package com.moayo.server.dao;

import com.moayo.server.model.HashtagModel;

import java.util.List;

public interface HashtagDao {
    long insertHashtag(HashtagModel hashtagModel);
    long deleteHashtag(HashtagModel hashtagModel);
    long deleteHashtagByString(String hashtag);
    List<HashtagModel> getAllHashtag();
}
