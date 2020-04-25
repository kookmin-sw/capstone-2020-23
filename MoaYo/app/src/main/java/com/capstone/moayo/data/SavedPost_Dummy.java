package com.capstone.moayo.data;

import com.capstone.moayo.model.SavedPost;

import java.util.ArrayList;

public class SavedPost_Dummy {

    ArrayList<SavedPost> food = new ArrayList<>();
    ArrayList<SavedPost> singer = new ArrayList<>();


    public SavedPost_Dummy() {

        food = new ArrayList<>();
        singer = new ArrayList<>();

    }

    public ArrayList<SavedPost> getFood() {

        SavedPost save1 = new SavedPost("https://www.simbata.co.kr/img_src/s600/a897/a8970104.jpg",
                "#유산슬맛집");
        SavedPost save2 = new SavedPost("https://www.simbata.co.kr/img_src/s600/a897/a8970355.jpg",
                "#유산슬밥");
        SavedPost save3 = new SavedPost("https://homecuisine.co.kr/files/attach/images/142/821/001/31c249d8f3fe7ff55a910c988e21faf8.JPG",
                "#유산슬면");
        SavedPost save4 = new SavedPost("https://lh3.googleusercontent.com/proxy/BD_3JnurRH4VzFj874wukoQbHNMjzdiOv4Kg1T2SjpLsGqn72R4dr7CvAdDcVHU9BiD-s0WXgH1t0wv0vc0GwKIhs8bChI9Qfatbvbn9wLyKoq26hfszZ8Ib6zMPMpPZJzuepifUTuWThIlq8uAWuIDPQbullCGMiAJNc3c8jCr5nRf8OEjyQ2HcBlojb_vB30bJnH_S1_xtIifPRya2WPrweQxvvpP5VZxj13cwF60Mup0KJLniVPiaEvRQzFtOEd9HeNJ_VWK9aHuXn8I9ncvmiI_Hc9H4aw",
                "#유산슬");

        food.add(save1);
        food.add(save2);
        food.add(save3);
        food.add(save4);

        return food;
    }

    public ArrayList<SavedPost> getSinger() {

        SavedPost save1 = new SavedPost("https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-15/e35/s1080x1080/93819799_537545896944480_5991584861600283703_n.jpg?_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_cat=100&_nc_ohc=fYvVBAkLq9YAX9feMtC&oh=ef3b4ebec501b9bbe4196bbb31a48e89&oe=5ECA7151",
                "#사랑의재개발");
        SavedPost save2 = new SavedPost("https://t1.daumcdn.net/news/202003/27/newsen/20200327080327290ibbj.jpg",
                "#유산슬1.5집");
        SavedPost save3 = new SavedPost("https://image.bugsm.co.kr/album/images/500/203150/20315029.jpg",
                "#유산슬송가인");
        SavedPost save4 = new SavedPost("https://newsimg.sedaily.com/2020/01/04/1YXIM3UEO1_1.jpg",
                "#유산슬뽕포유");

        singer.add(save1);
        singer.add(save2);
        singer.add(save3);
        singer.add(save4);

        return singer;
    }
}
