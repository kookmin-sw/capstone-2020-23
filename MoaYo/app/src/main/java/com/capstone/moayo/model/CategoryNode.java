package com.capstone.moayo.model;

import android.widget.ImageView;

import java.io.Serializable;
import java.util.ArrayList;

public class CategoryNode implements Serializable {

    public String title;
    public ArrayList<CategoryNode> lowLayer;
    public ArrayList<String> contents;

    private String url;
    private CategoryNode parent;
    private int level;

    private int id;

    //이미지 할당


    public CategoryNode(String title) {
        this.title = title;
        this.parent = null;
        this.level = 0;
        this.lowLayer = new ArrayList<CategoryNode>();
        this.contents = new ArrayList<String>();
        this.url = "";
        this.id = 0;
    }

    public CategoryNode(String title, String url) {
        this.title = title;
        this.parent = null;
        this.level = 0;
        this.lowLayer = new ArrayList<CategoryNode>();
        this.contents = new ArrayList<String>();
        this.url = url;
    }

    public CategoryNode(String title, String url, int id) {
        this.title = title;
        this.parent = null;
        this.level = 0;
        this.lowLayer = new ArrayList<CategoryNode>();
        this.contents = new ArrayList<String>();
        this.url = url;

        this.id = id;
    }


    public void addChild(CategoryNode node) {
        lowLayer.add(node);
    }

    public int getId() {return this.id; }
    //이미지 url 추가
    public String getUrl() {
        return url;
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