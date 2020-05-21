package com.capstone.moayo.entity.Model;

import com.google.gson.annotations.SerializedName;

public class DogamModel {
    @SerializedName("co_dogamId")
    private int id;
    @SerializedName("co_title")
    private String title;
    @SerializedName("co_description")
    private String description;
    @SerializedName("co_status")
    private int status;
    @SerializedName("co_password")
    private String password;

    public DogamModel(int id, String title, String description, int status, String password) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "DogamModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", password='" + password + '\'' +
                '}';
    }
}
