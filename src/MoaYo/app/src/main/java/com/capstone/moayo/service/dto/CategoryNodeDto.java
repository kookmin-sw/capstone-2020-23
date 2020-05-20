package com.capstone.moayo.service.dto;

import com.capstone.moayo.entity.CategoryNode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CategoryNodeDto {
    private int id;
    private String title;
    private int level;
    private List<String> hashtags;

    private CategoryNodeDto parent;
    private List<CategoryNodeDto> lowLayer;
    private List<PostDto> posts;

    public CategoryNodeDto() {
        this.id = 0;
        hashtags = new ArrayList<>();
        lowLayer = new ArrayList<>();
        posts = new ArrayList<>();
    }
    public CategoryNodeDto(String title, CategoryNodeDto parent, int level) {
        this();
        this.title = title;
        this.parent = parent;
        this.level = level;
    }

    public CategoryNode toCategoryNode(){
        CategoryNode rootNode = new CategoryNode(title, null, level); // 1st node
        rootNode.setId(id);
        for (CategoryNodeDto secondNodeDto : lowLayer) {
            CategoryNode secondNode = new CategoryNode(secondNodeDto.getTitle(), rootNode, secondNodeDto.getLevel()); // 2nd node
            secondNode.setId(secondNodeDto.getId());
            for (CategoryNodeDto thirdNodeDto : secondNodeDto.getLowLayer()) {
                CategoryNode thirdNode = new CategoryNode(thirdNodeDto.getTitle(), secondNode, thirdNodeDto.getLevel()); // 3rd node
                thirdNode.setId(thirdNodeDto.getId());
                secondNode.getLowLayer().add(thirdNode);
            }
            rootNode.getLowLayer().add(secondNode);
        }
        return rootNode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id;}
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CategoryNodeDto getParent() {
        return parent;
    }

    public void setParent(CategoryNodeDto parent) {
        this.parent = parent;
    }

    public List<String> getHashtags() {
        if(hashtags == null)
            hashtags = new ArrayList<>();
        return hashtags;
    }

    public void setHashtags(List<String> hashtags) {
        this.hashtags = hashtags;
    }

    public List<PostDto> getPosts() {
        return posts;
    }

    public void setPosts(List<PostDto> posts) {
        this.posts = posts;
    }

    public List<CategoryNodeDto> getLowLayer() {
        if(lowLayer == null)
            lowLayer = new ArrayList<>();
        return lowLayer;
    }

    public void setLowLayer(List<CategoryNodeDto> lowLayer) {
        this.lowLayer = lowLayer;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("{\"title\" : ").append("\"").append(title).append("\"").append(",").
                append("\"level\" : ").append("\"").append(level).append("\"").append(",").
                append("\"lowLayer\" : [");
        for(CategoryNodeDto lowNode : lowLayer) {
            buffer.append(lowNode.toString());
        }
        buffer.append("]}");

        return buffer.toString();
    }

}
