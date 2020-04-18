package com.capstone.moayo;

import java.io.Serializable;
import java.util.ArrayList;

public class CategoryNode implements Serializable {

    public String title;
    public ArrayList<CategoryNode> lowLayer;
    public ArrayList<String> contents;

    private CategoryNode parent;
    private int level;
    private int id;


    public CategoryNode(String title) {
        this.title = title;
        this.parent = null;
        this.level = 0;
        this.lowLayer = new ArrayList<CategoryNode>();
        this.contents = new ArrayList<String>();
    }

    public void addChild(CategoryNode node) {
        lowLayer.add(node);
    }

//    public BookData(String title, BookData parent, int level) {
//        this.title = title;
//        this.parent = parent;
//        this.level = level;
//
//        lowLayer = new ArrayList<String>();
////        contents = new ArrayList<String>();
//    }
}
