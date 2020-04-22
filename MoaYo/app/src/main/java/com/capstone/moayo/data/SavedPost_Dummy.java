package com.capstone.moayo.data;

import com.capstone.moayo.model.SavedPost;

import java.util.ArrayList;

public class SavedPost_Dummy {

    ArrayList<SavedPost> posts = new ArrayList<>();


    public SavedPost_Dummy() {
        posts = new ArrayList<>();
    }

    public ArrayList<SavedPost> getItems() {

        SavedPost save1 = new SavedPost("https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-15/e35/s1080x1080/93819799_537545896944480_5991584861600283703_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_cat=100&_nc_ohc=fYvVBAkLq9YAX9feMtC&oh=ef3b4ebec501b9bbe4196bbb31a48e89&oe=5ECA7151",
                "#사랑의재개발");
        SavedPost save2 = new SavedPost("https://t1.daumcdn.net/news/202003/27/newsen/20200327080327290ibbj.jpg",
                "#유산슬1.5집");
        SavedPost save3 = new SavedPost("https://image.bugsm.co.kr/album/images/500/203150/20315029.jpg",
                "#유산슬송가인");
        SavedPost save4 = new SavedPost("https://newsimg.sedaily.com/2020/01/04/1YXIM3UEO1_1.jpg",
                "#유산슬뽕포유");

        posts.add(save1);
        posts.add(save2);
        posts.add(save3);
        posts.add(save4);

        return posts;
    }
}
