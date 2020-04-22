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
        CategoryNode movie = new CategoryNode("영화");

        CategoryNode romance = new CategoryNode("로맨스");
        CategoryNode thriller = new CategoryNode("스릴러");

        CategoryNode horror = new CategoryNode("호러");
        CategoryNode scary_movie = new CategoryNode("무서운영화");
        horror.addChild(scary_movie);

        CategoryNode comedy = new CategoryNode("코미디");
        CategoryNode fantasy = new CategoryNode("판타지");

        movie.addChild(romance);
        movie.addChild(thriller);
        movie.addChild(horror);
        movie.addChild(comedy);
        movie.addChild(fantasy);


        //      Node <<Fashion>>
        CategoryNode fashion = new CategoryNode("패션");

        CategoryNode pants = new CategoryNode("하의");
        CategoryNode denim_pants = new CategoryNode("데님바지");
        CategoryNode cotton_pants = new CategoryNode("코튼바지");
        CategoryNode leather_pants = new CategoryNode("레더바지");

        pants.addChild(denim_pants);
        pants.addChild(cotton_pants);
        pants.addChild(leather_pants);

        CategoryNode top = new CategoryNode("상의");
        CategoryNode hood = new CategoryNode("후드");

        top.addChild(hood);

        CategoryNode outer = new CategoryNode("아우터");
        CategoryNode denim_jacket = new CategoryNode("데님자켓");
        CategoryNode cotton_jacket = new CategoryNode("코튼자켓");
        CategoryNode leather_jacket = new CategoryNode("레더자켓");

        outer.addChild(denim_jacket);
        outer.addChild(cotton_jacket);
        outer.addChild(leather_jacket);

        fashion.addChild(pants);
        fashion.addChild(top);
        fashion.addChild(outer);

        //  Node <<Singer>>
        CategoryNode singer = new CategoryNode("가수");

        CategoryNode trot = new CategoryNode("트로트");
        CategoryNode ballad = new CategoryNode("발라드");
        CategoryNode hiphop = new CategoryNode("힙합");
        CategoryNode rock = new CategoryNode("락");
        CategoryNode dance = new CategoryNode("댄스");

        CategoryNode yoosanseul = new CategoryNode("유산슬");
        CategoryNode songgain = new CategoryNode("송가인");
        CategoryNode jangyunjung = new CategoryNode("장윤정");
        CategoryNode hongginyoung = new CategoryNode("홍진영");

        trot.addChild(yoosanseul);
        trot.addChild(songgain);
        trot.addChild(jangyunjung);
        trot.addChild(hongginyoung);

        singer.addChild(trot);
        singer.addChild(ballad);
        singer.addChild(hiphop);
        singer.addChild(rock);
        singer.addChild(dance);

        nodes.add(singer);
        nodes.add(fashion);
        nodes.add(movie);

        return nodes;
    }
}


