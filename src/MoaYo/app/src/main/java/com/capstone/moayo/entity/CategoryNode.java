package com.capstone.moayo.entity;

import com.capstone.moayo.service.dto.CategoryNodeDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CategoryNode implements Serializable {

    private int id;
    private String title;
    private String url;
    private int level;
    private List<String> hashtags;
    private int dogamId;
    private int parentDogamId;

    private CategoryNode parent;
    private List<CategoryNode> lowLayer;
    private List<Post> posts;

    public CategoryNode() {
        this.id = 0;
        hashtags = new ArrayList<>();
        lowLayer = new ArrayList<>();
        posts = new ArrayList<>();
    }

    public CategoryNode(String title, CategoryNode parent, int level) {
        this();
        this.title = title;
        this.parent = parent;
        this.level = level;
    }

    public CategoryNodeDto toCategoryNodeDto() {
        CategoryNodeDto rootNode = new CategoryNodeDto(title, null, level);
        rootNode.setId(id);
        rootNode.setHashtags(hashtags);
        for(CategoryNode secondNode : lowLayer) {
            CategoryNodeDto secondNodeDto = new CategoryNodeDto(secondNode.getTitle(), rootNode, secondNode.getLevel());
            secondNodeDto.setId(secondNode.getId());
            secondNodeDto.setHashtags(secondNode.getHashtags());
            for(CategoryNode thirdNode : secondNode.getLowLayer()) {
                CategoryNodeDto thirdNodeDto = new CategoryNodeDto(thirdNode.getTitle(), secondNodeDto, thirdNode.getLevel());
                thirdNodeDto.setId(thirdNode.getId());
                thirdNodeDto.setHashtags(thirdNode.getHashtags());
                secondNodeDto.getLowLayer().add(thirdNodeDto);
            }
            rootNode.getLowLayer().add(secondNodeDto);
        }

        return rootNode;
    }

    public void setDogamId(int dogamId) {
        this.dogamId = dogamId;
    }

    public int getDogamId() {
        return dogamId;
    }

    public void setParentDogamId(int parentDogamId) {
        this.parentDogamId = parentDogamId;
    }

    public int getParentDogamId() {
        return parentDogamId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<String> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<String> hashtags) {
        this.hashtags = hashtags;
    }

    public List<CategoryNode> getLowLayer() {
        if(lowLayer != null)
            return lowLayer;
        else {
            this.lowLayer = new ArrayList<CategoryNode>();
            return lowLayer;
        }
    }

    public void addLowLayer(CategoryNode node) { this.lowLayer.add(node); }

    public void setLowLayer(List<CategoryNode> lowLayer) {this.lowLayer = lowLayer;}

    public void setParent(CategoryNode parent) {
        this.parent = parent;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) { this.url = url; }

    public CategoryNode getParent() {
        return parent;
    }

    public int getLevel() {
        return level;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() { return url; }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("{\"id\" : ").append("\"").append(id).append("\"").append(",").
                append("\"title\" : ").append("\"").append(title).append("\"").append(",").
                append("\"level\" : ").append("\"").append(level).append("\"").append(",").
                append("\"parentId\" : ").append("\"").append(parent.getId()).append(",").
                append("\"lowLayer\" : [");
        for(CategoryNode lowNode : lowLayer) {
            buffer.append(lowNode.toString());
        }
        buffer.append("]}");

        return buffer.toString();
    }
}
