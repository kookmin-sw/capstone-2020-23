package com.capstone.moayo.dao.mapping;

public class DogamMapping {
    private int id;
    private String title;
    private String desription;
    private String password;

    public void setDesription(String desription) {
        this.desription = desription;
    }

    public String getDesription() {
        return desription;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
