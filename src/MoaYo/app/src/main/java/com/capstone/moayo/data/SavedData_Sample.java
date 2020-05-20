package com.capstone.moayo.data;

import com.capstone.moayo.model.NewPost;
import com.capstone.moayo.model.SavedPost;

import java.util.ArrayList;

public class SavedData_Sample {

    ArrayList<SavedPost> saveditems = new ArrayList<>();

    public ArrayList<SavedPost> getItems() {

        SavedPost save1 = new SavedPost("https://lh3.googleusercontent.com/proxy/r-Q0gAWNlhzeOaNMq-C_0_9o1g9duEq46spSyTnwCippT4F5Wl27zbA6tlrDLHLfYxA0h-NLPeRQDZqAl282RhicYHCw_hpmnylMZ-I7iLDbUHiL9eLGPLvNvfgu22Fmb035ixAAsy9Jd_jmWztKIbaUYyiuxnxWkJu62w",
                "#유산슬뽕포유");

        SavedPost save2 = new SavedPost("https://t1.daumcdn.net/news/202003/27/newsen/20200327080327290ibbj.jpg",
                "#유산슬1.5집");

        SavedPost save3 = new SavedPost("https://image.bugsm.co.kr/album/images/500/203150/20315029.jpg",
                "#유산슬송가인");

        SavedPost save4 = new SavedPost("https://newsimg.sedaily.com/2020/01/04/1YXIM3UEO1_1.jpg",
                "#사랑의재개발");

        saveditems.add(save1);
        saveditems.add(save2);
        saveditems.add(save3);
        saveditems.add(save4);

        saveditems.add(save1);
        saveditems.add(save2);
        saveditems.add(save3);
        saveditems.add(save4);

        saveditems.add(save1);
        saveditems.add(save2);
        saveditems.add(save3);
        saveditems.add(save4);

        saveditems.add(save1);
        saveditems.add(save2);
        saveditems.add(save3);
        saveditems.add(save4);

        saveditems.add(save1);
        saveditems.add(save2);
        saveditems.add(save3);
        saveditems.add(save4);

        return saveditems;
    }
}
