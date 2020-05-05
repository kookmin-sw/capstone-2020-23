package com.capstone.moayo.storage;

import java.util.List;

public interface HashtagStorage {
    public String create(List<String> hashtagList);
    public List<String> retrieveByCategoryId(int id);
    public void update(List<String> hashtagList);
    public void remove(String hashtag);
}
