package com.capstone.moayo.entity;

import com.capstone.moayo.service.dto.CategoryNodeDto;
import com.capstone.moayo.service.dto.ContentDto;
import com.capstone.moayo.util.Exception.NotRootException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CategoryNode {
    private int id;
    private String title;
    private CategoryNode parent;
    private List<CategoryNode> lowLayer;
    private int level;


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
    }

    public CategoryNodeDto toCategoryNodeDto() {
        CategoryNodeDto rootNode = null;
        try {
            if(parent != null) throw new NotRootException();
            rootNode = new CategoryNodeDto(title, null, level);
            for(CategoryNode secondNode : lowLayer) {
                CategoryNodeDto secondNodeDto = new CategoryNodeDto(secondNode.getTitle(), rootNode, secondNode.getLevel());
                for(CategoryNode thirdNode : secondNode.getLowLayer()) {
                    CategoryNodeDto thirdNodeDto = new CategoryNodeDto(thirdNode.getTitle(), secondNodeDto, thirdNode.getLevel());
                }
            }
        } catch (NotRootException e) {
            e.printStackTrace();
        } finally {
            return rootNode;
        }

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

    public List<CategoryNode> getLowLayer() {
        if(lowLayer != null)
            return lowLayer;
        else {
            this.lowLayer = new ArrayList<CategoryNode>();
            return lowLayer;
        }
    }

    public void setLowLayer(List<CategoryNode> lowLayer) {this.lowLayer = lowLayer;}

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

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("{\"id\" : ").append("\"").append(id).append("\"").append(",").
                append("\"title\" : ").append("\"").append(title).append("\"").append(",").
                append("\"level\" : ").append("\"").append(level).append("\"").append(",").
                append("\"lowLayer\" : [");
        for(CategoryNode lowNode : lowLayer) {
            buffer.append(lowNode.toString());
        }
        buffer.append("]}");

        return buffer.toString();
    }
}
