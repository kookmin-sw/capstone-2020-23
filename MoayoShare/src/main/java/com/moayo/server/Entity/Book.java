package com.moayo.server.Entity;

import org.json.simple.JSONArray;

public class Book {

    private String tag;
    private String password;

    private JSONArray array;

    public Book(String tag,String password,JSONArray array){
        this.tag = tag;
        this.password = password;
        this.array = array;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public JSONArray getArray() {
        return array;
    }

    public void setArray(JSONArray array) {
        this.array = array;
    }
}
