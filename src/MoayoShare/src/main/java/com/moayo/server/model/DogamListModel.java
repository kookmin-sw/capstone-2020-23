package com.moayo.server.model;

public class DogamListModel {
    private int co_dogamId;
    private String co_title;
    private String co_description;
    private int co_status;
    private String co_password;

    public DogamListModel(int co_dogamId, String co_title, String co_description, int co_status, String co_password) {
        this.co_dogamId = co_dogamId;
        this.co_title = co_title;
        this.co_description = co_description;
        this.co_status = co_status;
        this.co_password = co_password;
    }

    public DogamListModel(String co_title, String co_description, int co_status, String co_password) {
        this.co_title = co_title;
        this.co_description = co_description;
        this.co_status = co_status;
        this.co_password = co_password;
    }

    public DogamListModel() {

    }


    public void setCo_title(String co_title) {
        this.co_title = co_title;
    }

    public String getCo_title() {
        return co_title;
    }

    public void setCo_password(String co_password) {
        this.co_password = co_password;
    }

    public String getCo_password() {
        return co_password;
    }

    public void setCo_description(String co_description) {
        this.co_description = co_description;
    }

    public int getCo_dogamId() {
        return co_dogamId;
    }

    public void setCo_dogamId(int co_dogamId) {
        this.co_dogamId = co_dogamId;
    }

    public int getCo_status() {
        return co_status;
    }

    public void setCo_status(int co_status) {
        this.co_status = co_status;
    }

    public String getCo_description() {
        return co_description;
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append("{").append(getCo_dogamId()).append(",").append(getCo_title()).append("}");
        return result.toString();
    }
}
