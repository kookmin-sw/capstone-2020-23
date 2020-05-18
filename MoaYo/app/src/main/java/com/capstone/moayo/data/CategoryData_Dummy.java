package com.capstone.moayo.data;

import com.capstone.moayo.entity.CategoryNode;

import java.util.ArrayList;

public class CategoryData_Dummy {

    ArrayList<CategoryNode> nodes;

    public CategoryData_Dummy() {
        nodes = new ArrayList<>();
    }

    public ArrayList<CategoryNode> getItems() {
        //      Node <<Movie>>
        CategoryNode movie = new CategoryNode("영화", null, 1);
        movie.setUrl("https://t1.daumcdn.net/movie/da2e6f0663514ba3aaf1f003733d08831560262646934");

        CategoryNode romance = new CategoryNode("로맨스", movie, 2);
        CategoryNode thriller = new CategoryNode("스릴러", movie, 2);
        CategoryNode horror = new CategoryNode("호러", movie,2);
        CategoryNode comedy = new CategoryNode("코미디", movie, 2);
        CategoryNode fantasy = new CategoryNode("판타지", movie, 2);

        CategoryNode scary_movie = new CategoryNode("무서운영화", horror, 3);

        ArrayList<CategoryNode> horror_lowlayer = new ArrayList<>();
        horror_lowlayer.add(scary_movie);
        horror.setLowLayer(horror_lowlayer);

        ArrayList<CategoryNode> movie_lowlayer = new ArrayList<>();
        movie_lowlayer.add(romance);
        movie_lowlayer.add(thriller);
        movie_lowlayer.add(horror);
        movie_lowlayer.add(fantasy);

        movie.setLowLayer(movie_lowlayer);


        //      Node <<Fashion>>
        CategoryNode fashion = new CategoryNode("패션", null, 1);
        fashion.setUrl("https://usercontents-c.styleshare.io/images/19518035/640x-");
//
//        CategoryNode pants = new CategoryNode("하의", fashion, );
//        CategoryNode denim_pants = new CategoryNode("데님바지","");
//        CategoryNode cotton_pants = new CategoryNode("코튼바지","");
//        CategoryNode leather_pants = new CategoryNode("레더바지","");
//
//        pants.addChild(denim_pants);
//        pants.addChild(cotton_pants);
//        pants.addChild(leather_pants);
//
//        CategoryNode top = new CategoryNode("상의","");
//        CategoryNode hood = new CategoryNode("후드","");
//
//        top.addChild(hood);
//
//        CategoryNode outer = new CategoryNode("아우터","");
//        CategoryNode denim_jacket = new CategoryNode("데님자켓","");
//        CategoryNode cotton_jacket = new CategoryNode("코튼자켓","");
//        CategoryNode leather_jacket = new CategoryNode("레더자켓","");
//
//        outer.addChild(denim_jacket);
//        outer.addChild(cotton_jacket);
//        outer.addChild(leather_jacket);
//
//        fashion.addChild(pants);
//        fashion.addChild(top);
//        fashion.addChild(outer);

        //  Node <<Singer>>
        CategoryNode singer = new CategoryNode("가수", null, 1);
        singer.setUrl("https://img1.daumcdn.net/thumb/R720x0.q80/?scode=mtistory2&fname=http%3A%2F%2Fcfile5.uf.tistory.com%2Fimage%2F99C86B485DCC0E3314477E");
        CategoryNode trot = new CategoryNode("트로트", singer, 2);
        CategoryNode ballad = new CategoryNode("발라드", singer, 2);
        CategoryNode hiphop = new CategoryNode("힙합", singer, 2);
        CategoryNode rock = new CategoryNode("락", singer, 2);
        CategoryNode dance = new CategoryNode("댄스", singer, 2);

        CategoryNode yoosanseul = new CategoryNode("유산슬", trot, 3);
        CategoryNode songgain = new CategoryNode("송가인", trot, 3);
        CategoryNode jangyunjung = new CategoryNode("장윤정", trot, 3);
        CategoryNode hongginyoung = new CategoryNode("홍진영", trot, 3);

        ArrayList<CategoryNode> trot_lowlayer = new ArrayList<>();
        trot_lowlayer.add(yoosanseul);
        trot_lowlayer.add(songgain);
        trot_lowlayer.add(jangyunjung);
        trot_lowlayer.add(hongginyoung);

        trot.setLowLayer(trot_lowlayer);

        ArrayList<CategoryNode> singer_lowlayer = new ArrayList<>();
        singer_lowlayer.add(trot);
        singer_lowlayer.add(ballad);
        singer_lowlayer.add(hiphop);
        singer_lowlayer.add(rock);
        singer_lowlayer.add(dance);

        singer.setLowLayer(singer_lowlayer);


        //맛집
        CategoryNode food = new CategoryNode("맛집", null, 1);
        food.setUrl("https://www.polinews.co.kr/data/photos/20190102/art_15469376601633_583641.jpg");

        CategoryNode korean_food = new CategoryNode("한식", food, 2);
        CategoryNode japenese_food = new CategoryNode("일식", food, 2);
        CategoryNode chinese_food = new CategoryNode("중식", food, 2);
        CategoryNode snack = new CategoryNode("분식", food, 2);
        CategoryNode fast_food = new CategoryNode("패스트푸드", food, 2);
        CategoryNode dessert = new CategoryNode("디저트", food, 2);

        CategoryNode blacknoodle = new CategoryNode("짜장면", chinese_food, 3);
        CategoryNode jjamppong = new CategoryNode("짬뽕", chinese_food, 3);
        CategoryNode sour_pork = new CategoryNode("탕수육", chinese_food, 3);
        CategoryNode yoosanseul_food = new CategoryNode("유산슬", chinese_food, 3);
        CategoryNode palbochae = new CategoryNode("팔보채", chinese_food, 3);
        CategoryNode china_chicken = new CategoryNode("깐풍기", chinese_food, 3);

        ArrayList<CategoryNode> chinese_lowlayer = new ArrayList<>();
        chinese_lowlayer.add(blacknoodle);
        chinese_lowlayer.add(jjamppong);
        chinese_lowlayer.add(sour_pork);
        chinese_lowlayer.add(yoosanseul_food);
        chinese_lowlayer.add(palbochae);
        chinese_lowlayer.add(china_chicken);
        chinese_food.setLowLayer(chinese_lowlayer);

        ArrayList<CategoryNode> food_lowlayer = new ArrayList<>();
        food_lowlayer.add(korean_food);
        food_lowlayer.add(japenese_food);
        food_lowlayer.add(chinese_food);
        food_lowlayer.add(snack);
        food_lowlayer.add(fast_food);
        food_lowlayer.add(dessert);
        food.setLowLayer(food_lowlayer);



        //가구
        CategoryNode furniture = new CategoryNode("가구", null, 1);
        furniture.setUrl("https://post-phinf.pstatic.net/MjAxODA2MTVfMjI3/MDAxNTI5MDI0OTEyNzkw.nZysM1B1Cph53JAl2hPk91kh5p1C7P1EZfV_vfiSGsMg.CYE_l4wAKZ-qKfmEFH_WdZ0uDz7x-GYsd3e3Hr1232Ag.PNG/6.png?type=w800_q75");

        //여행
        CategoryNode travel = new CategoryNode("여행", null, 1);
        travel.setUrl("https://image.edaily.co.kr/images/Photo/files/NP/S/2016/06/PS16060300126.jpg");

        //Dummy CategoryNode
        CategoryNode dummy = new CategoryNode("더미", null, 1);
        dummy.setUrl("https://image.flaticon.com/icons/png/512/130/130304.png");


        nodes.add(singer);
        nodes.add(fashion);
        nodes.add(movie);
        nodes.add(food);
        nodes.add(furniture);
        nodes.add(travel);
        nodes.add(dummy);
        nodes.add(dummy);
        nodes.add(dummy);
        nodes.add(dummy);
        nodes.add(dummy);
        nodes.add(dummy);
        nodes.add(dummy);
        nodes.add(dummy);


        return nodes;
    }
}


