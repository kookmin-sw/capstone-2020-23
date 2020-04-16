package com.capstone.moayo.service.dto;

import com.capstone.moayo.entity.CategoryNode;
import com.capstone.moayo.util.Exception.NotRootException;

import java.util.ArrayList;
import java.util.List;

public class CategoryNodeDto {
    private String title;
    private CategoryNodeDto parent;
    private List<CategoryNodeDto> lowLayer;
    private int level;

    public CategoryNodeDto(String title, CategoryNodeDto parent, int level) {
        this.title = title;
        this.parent = parent;
        this.level = level;

        this.lowLayer = new ArrayList<>();
    }

    public CategoryNode toCategoryNode(){
        CategoryNode rootNode = new CategoryNode(title, null, level); // 1st node
        for (CategoryNodeDto secondNodeDto : lowLayer) {
            CategoryNode secondNode = new CategoryNode(secondNodeDto.getTitle(), rootNode, secondNodeDto.getLevel()); // 2nd node
            for (CategoryNodeDto thirdNodeDto : secondNodeDto.getLowLayer()) {
                CategoryNode thirdNode = new CategoryNode(thirdNodeDto.getTitle(), secondNode, thirdNodeDto.getLevel()); // 3rd node
                secondNode.getLowLayer().add(thirdNode);
            }
            rootNode.getLowLayer().add(secondNode);
        }
        return rootNode;
    }

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

    public List<CategoryNodeDto> getLowLayer() {
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
