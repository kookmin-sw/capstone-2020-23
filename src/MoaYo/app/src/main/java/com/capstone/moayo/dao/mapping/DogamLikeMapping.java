package com.capstone.moayo.dao.mapping;

public class DogamLikeMapping {
    private int dogamId;
    private boolean isLiked;

    public DogamLikeMapping(int dogamId, int isLiked) {
        this.dogamId = dogamId;
        if(isLiked == 0)
            this.isLiked = true;
        else
            this.isLiked = false;
    }

    public int getDogamId() {
        return dogamId;
    }

    public void setDogamId(int dogamId) {
        this.dogamId = dogamId;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }
}
