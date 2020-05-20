package com.capstone.moayo.model;

import java.util.ArrayList;

public class BookData {

    public String title;
    public ArrayList<String> lowLayer;
//    public ArrayList<String> contents;

    private BookData parent;
    private int level;
    private int id;


    public BookData(String title) {
        this.title = title;
        this.parent = null;
        this.lowLayer = null;
        this.level = 0;
        this.lowLayer = new ArrayList<String>();
    }

    public void addChild(String child) {
        lowLayer.add(child);
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