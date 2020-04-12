package com.capstone.moayo.entity;

import com.capstone.moayo.service.dto.CategoryNodeDto;
import com.capstone.moayo.service.dto.ContentDto;
import com.capstone.moayo.util.Exception.NotRootException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CategoryNode {
    private String title;
    private CategoryNode parent;
    private List<CategoryNode> lowLayer;
    private int level;

    private List<Content> contents;

    public CategoryNode() {
        this.title = null;
        this.parent = null;
        this.lowLayer = null;
        this.level = 0;
    }

    public CategoryNode(String title, CategoryNode parent, int level) {
        this.title = title;
        this.parent = parent;
        this.level = level;

        lowLayer = new ArrayList<CategoryNode>();
        contents = new ArrayList<>();
    }

    public CategoryNodeDto toCategoryNodeDto() {
        CategoryNodeDto rootNode = null;
        try {
            if(parent != null) throw new NotRootException();
            rootNode = new CategoryNodeDto(title, null, level);
            List<ContentDto> contentDtos = new ArrayList<>();
            for(CategoryNode secondNode : lowLayer) {
                CategoryNodeDto secondNodeDto = new CategoryNodeDto(secondNode.getTitle(), rootNode, secondNode.getLevel());
                for(Content content : secondNode.getContents()) {
                    ContentDto contentDto = content.toContentDto();
                    contentDtos.add(contentDto);
                }
                secondNodeDto.setContents(contentDtos);
                contentDtos.clear();
                for(CategoryNode thirdNode : secondNode.getLowLayer()) {
                    CategoryNodeDto thirdNodeDto = new CategoryNodeDto(thirdNode.getTitle(), secondNodeDto, thirdNode.getLevel());
                    for (Content content : thirdNode.getContents()) {
                        ContentDto contentDto = content.toContentDto();
                        contentDtos.add(contentDto);
                    }
                    thirdNodeDto.setContents(contentDtos);
                    contentDtos.clear();
                }
            }
        } catch (NotRootException e) {
            e.printStackTrace();
        } finally {
            return rootNode;
        }

    }
    public void setLevel(int level) {
        this.level = level;
    }

    public List<CategoryNode> getLowLayer() {
        return lowLayer;
    }

    public void setLowLayer(List<CategoryNode> lowLayer) {
        this.lowLayer = lowLayer;
    }

    public void setParent(CategoryNode parent) {
        this.parent = parent;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CategoryNode getParent() {
        return parent;
    }

    public int getLevel() {
        return level;
    }

    public String getTitle() {
        return title;
    }

    public List<Content> getContents() { return contents; }

    public void setContents(List<Content> contents) { this.contents = contents; }
}
