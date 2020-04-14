package com.capstone.moayo.data;


import com.capstone.moayo.model.NewPost;

import java.util.ArrayList;

public class RecommendData_Sample {

    ArrayList<NewPost> items = new ArrayList<>();

    public ArrayList<NewPost> getItems() {

        NewPost new1 = new NewPost("https://file.mk.co.kr/meet/neds/2019/11/image_readtop_2019_946315_15737211773976737.jpg");

        NewPost new2 = new NewPost("https://post-phinf.pstatic.net/MjAxOTA5MjBfMTkw/MDAxNTY4OTU4ODQ0MzQz.LafQDluadjb6bHtQEqCpV3Qf0cNHs75hc5VryAvQ4IYg.Qq7q4KHJmn81fHcnImIVj4opK5kswDKtPGT5Fs00E9Ag.JPEG/hangout_with_yoo_71188186_972766079728373_4444266538588771361_n.jpg?type=w1200");

        NewPost new3 = new NewPost("https://www.topstarnews.net/news/photo/202001/725649_440660_353.jpg");

        NewPost new4 = new NewPost("https://thumb.mt.co.kr/06/2019/10/2019101314542538254_1.jpg/dims/optimize/");

        items.add(new1);
        items.add(new2);
        items.add(new3);
        items.add(new4);

        items.add(new1);
        items.add(new2);
        items.add(new3);
        items.add(new4);

        items.add(new1);
        items.add(new2);
        items.add(new3);
        items.add(new4);

        items.add(new1);
        items.add(new2);
        items.add(new3);
        items.add(new4);

        items.add(new1);
        items.add(new2);
        items.add(new3);
        items.add(new4);


        return items;
    }
}
