package com.moayo.server.model;

public class BookModel {
    private int co_no;
    private String co_title;
    private String co_tag;
    private String co_password;
    private String co_url;
    private String co_writer;

    public void setCo_no(int co_no) {
        this.co_no = co_no;
    }

    public int getCo_no() {
        return co_no;
    }

    public void setCo_password(String co_password) {
        this.co_password = co_password;
    }

    public String getCo_password() {
        return co_password;
    }

    public void setCo_tag(String co_tag) {
        this.co_tag = co_tag;
    }

    public String getCo_tag() {
        return co_tag;
    }

    public void setCo_title(String co_title) {
        this.co_title = co_title;
    }

    public String getCo_title() {
        return co_title;
    }

    public void setCo_url(String co_url) {
        this.co_url = co_url;
    }

    public String getCo_url() {
        return co_url;
    }

    public void setCo_writer(String co_writer) {
        this.co_writer = co_writer;
    }

    public String getCo_writer() {
        return co_writer;
    }

    @Override
    public String toString(){
        return "title : " + getCo_title() + "\n writer : " + getCo_writer();
    }
}
