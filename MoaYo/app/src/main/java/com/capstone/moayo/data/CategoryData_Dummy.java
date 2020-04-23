package com.capstone.moayo.data;

import com.capstone.moayo.model.Category;
import com.capstone.moayo.model.CategoryNode;

import java.util.ArrayList;

public class CategoryData_Dummy {

    ArrayList<CategoryNode> nodes;

    public CategoryData_Dummy() {
        nodes = new ArrayList<>();
    }

    public ArrayList<CategoryNode> getItems() {
        //      Node <<Movie>>
        CategoryNode movie = new CategoryNode("영화","https://t1.daumcdn.net/movie/da2e6f0663514ba3aaf1f003733d08831560262646934");

        CategoryNode romance = new CategoryNode("로맨스","");
        CategoryNode thriller = new CategoryNode("스릴러","");

        CategoryNode horror = new CategoryNode("호러","");
        CategoryNode scary_movie = new CategoryNode("무서운영화","");
        horror.addChild(scary_movie);

        CategoryNode comedy = new CategoryNode("코미디","");
        CategoryNode fantasy = new CategoryNode("판타지","");

        movie.addChild(romance);
        movie.addChild(thriller);
        movie.addChild(horror);
        movie.addChild(comedy);
        movie.addChild(fantasy);


        //      Node <<Fashion>>
        CategoryNode fashion = new CategoryNode("패션","https://usercontents-c.styleshare.io/images/19518035/640x-");

        CategoryNode pants = new CategoryNode("하의","");
        CategoryNode denim_pants = new CategoryNode("데님바지","");
        CategoryNode cotton_pants = new CategoryNode("코튼바지","");
        CategoryNode leather_pants = new CategoryNode("레더바지","");

        pants.addChild(denim_pants);
        pants.addChild(cotton_pants);
        pants.addChild(leather_pants);

        CategoryNode top = new CategoryNode("상의","");
        CategoryNode hood = new CategoryNode("후드","");

        top.addChild(hood);

        CategoryNode outer = new CategoryNode("아우터","");
        CategoryNode denim_jacket = new CategoryNode("데님자켓","");
        CategoryNode cotton_jacket = new CategoryNode("코튼자켓","");
        CategoryNode leather_jacket = new CategoryNode("레더자켓","");

        outer.addChild(denim_jacket);
        outer.addChild(cotton_jacket);
        outer.addChild(leather_jacket);

        fashion.addChild(pants);
        fashion.addChild(top);
        fashion.addChild(outer);

        //  Node <<Singer>>
        CategoryNode singer = new CategoryNode("가수","https://post-phinf.pstatic.net/MjAxOTA5MjBfMTkw/MDAxNTY4OTU4ODQ0MzQz.LafQDluadjb6bHtQEqCpV3Qf0cNHs75hc5VryAvQ4IYg.Qq7q4KHJmn81fHcnImIVj4opK5kswDKtPGT5Fs00E9Ag.JPEG/hangout_with_yoo_71188186_972766079728373_4444266538588771361_n.jpg?type=w1200");

        CategoryNode trot = new CategoryNode("트로트","");
        CategoryNode ballad = new CategoryNode("발라드","");
        CategoryNode hiphop = new CategoryNode("힙합","");
        CategoryNode rock = new CategoryNode("락","");
        CategoryNode dance = new CategoryNode("댄스","");

        CategoryNode yoosanseul = new CategoryNode("유산슬","");
        CategoryNode songgain = new CategoryNode("송가인","");
        CategoryNode jangyunjung = new CategoryNode("장윤정","");
        CategoryNode hongginyoung = new CategoryNode("홍진영","");

        trot.addChild(yoosanseul);
        trot.addChild(songgain);
        trot.addChild(jangyunjung);
        trot.addChild(hongginyoung);

        singer.addChild(trot);
        singer.addChild(ballad);
        singer.addChild(hiphop);
        singer.addChild(rock);
        singer.addChild(dance);

        //맛집
        CategoryNode food = new CategoryNode("맛집","https://www.polinews.co.kr/data/photos/20190102/art_15469376601633_583641.jpg");

        //가구
        CategoryNode furniture = new CategoryNode("가구","https://post-phinf.pstatic.net/MjAxODA2MTVfMjI3/MDAxNTI5MDI0OTEyNzkw.nZysM1B1Cph53JAl2hPk91kh5p1C7P1EZfV_vfiSGsMg.CYE_l4wAKZ-qKfmEFH_WdZ0uDz7x-GYsd3e3Hr1232Ag.PNG/6.png?type=w800_q75");

        //여행
        CategoryNode travel = new CategoryNode("여행","https://travelpost.kr/wp-content/uploads/2016/02/78-01.jpg");

        nodes.add(singer);
        nodes.add(fashion);
        nodes.add(movie);
        nodes.add(food);
        nodes.add(furniture);
        nodes.add(travel);

        return nodes;
    }
}


